package es.sgad.trama.permiso.service.bandejaEnviados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.PantallaBandejaEnviadosDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoEnviadoDTO;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.mapper.bandejaEnviados.SolicitudPermisoEnviadoMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.ambito.AmbitoRepository;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoRepository;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosFilter;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosRepository;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosSpecification;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.estadoTramite.EstadoTramiteRepository;
import es.sgad.trama.permiso.persistance.usuario.UsuarioRepository;


@Service
public class BandejaEnviadosServiceImpl implements BandejaEnviadosService {

	@Autowired
	private FiltroBandejaEnviadosRepository bandejaEnviadosRepository;

	@Autowired
	private EstadoTramiteRepository estadoTramiteRepository;
	
	@Autowired
	private TipoPermisoAmbitoRepository tipoPermisoAmbitoRepository;
	
	@Autowired
	private SolicitudPermisoEnviadoMapper solicitudPermisoEnviadoMapper;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private AmbitoRepository ambitoRepository;
	
	// Devuelve los datos de la pantalla de bandeja de permisos enviados de un usuario para un ambito
	@Override
	public PantallaBandejaEnviadosDTO getPantallaBandejaEnviados(String usuarioId, String ambitoId) {
		
		// Crea y asigna la lista de estados a presentar en pantalla en los que se pueden 
		// encontrar las solicitudes de permiso enviados de la lista (Todos y En proceso)
		List<EstadoTramiteComboDTO> listaEstados = getListaConstantesEstado ();
		
		// Busca los estados que hay en BBDD
		List<EstadoTramiteComboDTO> listaEstadosBBDD = this.estadoTramiteRepository.findEstadoFinalForCombo();
		
		// Incorpora los estados de base de datos a la lista de estados a presentar en el combo
		for (EstadoTramiteComboDTO estado: listaEstadosBBDD) {
			listaEstados.add(estado);
			
		}
		
		// Busca los datos de los tipos de permiso del ambito
		AmbitoEntity ambito = ambitoRepository.findById(ambitoId).orElse(null);
		List<TipoPermisoAmbitoComboDTO> listaTipoPermisoAmbito = this.tipoPermisoAmbitoRepository.findTipoPermisoAmbitoForCombo(ambito);
		
		// Busca los usuarios solicitante de las solicitudes de permiso presentadas en la lista
		List<SolicitanteComboDTO> listaSolicitante = new ArrayList<SolicitanteComboDTO>();
		
		// Los solicitantes son el usuario logado por estar la opción "Creadas por mi" seleccionada en el combo Accion.
		listaSolicitante.add(usuarioMapper.toSolicitanteComboDTO(this.usuarioRepository.findById(usuarioId).orElse(null)));
		
		// Busca las 3 acciones que se pueden realizar sobre las solicitudes de permiso enviadas de la lista
		List<EstadoTramiteComboDTO> listaAccion = getListaConstantesAccion();
		
		// Busca las solicitudes de permiso enviadas de un usuario para un ambito
		List<SolicitudPermisoEntity> listaSolicitudesPermisoEnviado = this.bandejaEnviadosRepository.findListaSolicitudPermisoEnviado(ambitoId, usuarioId);
		
		// Convierte la lista de las solicitudes de permiso enviadas de un usuario para un ambito en una lista para presentar en pantalla
		List<SolicitudPermisoEnviadoDTO> listaPantallaSolicitudesPermisoEnviado = solicitudPermisoEnviadoMapper.toListaSolicitudPermisoEnviadoDTO(listaSolicitudesPermisoEnviado, Constantes.VALOR_STRING_CREADAS_POR_MI);
		
		PantallaBandejaEnviadosDTO pantallaBandejaEnviadosDTO = new PantallaBandejaEnviadosDTO();
		
		// Asignamos todos los datos de la pantalla
		pantallaBandejaEnviadosDTO.setEstadoCombo(listaEstados);
		pantallaBandejaEnviadosDTO.setSolicitanteCombo(listaSolicitante);
		pantallaBandejaEnviadosDTO.setSolicitudPermisoEnviado(listaPantallaSolicitudesPermisoEnviado);
		pantallaBandejaEnviadosDTO.setTipoPermisoAmbitoCombo(listaTipoPermisoAmbito);
		pantallaBandejaEnviadosDTO.setAccionCombo(listaAccion);
		
		return pantallaBandejaEnviadosDTO;

	}

	// Devuelve el error producido al intentar cargar la pantalla de bandeja de permisos enviados de un usuario para un ambito
	@Override
	public PantallaBandejaEnviadosDTO getPantallaBandejaEnviadosError(String mensajeError) {
	
		PantallaBandejaEnviadosDTO pantallaBandejaEnviadasDTO = new PantallaBandejaEnviadosDTO();
		
		return pantallaBandejaEnviadasDTO;
	}

	// Devuelve la lista de solicitudes de permisos enviados filtrados por las condiciones marcadas por los campos de la busqueda
	@Override
    public SearchPageResponse<SolicitudPermisoEnviadoDTO> searchBandejaEnviados(
    		FiltroBandejaEnviadosFilter filter, int page, int size) {
       
		Page<SolicitudPermisoEntity> pageResult = listaResultadosSolicitudPermisos(filter,  page,  size);
		
        return solicitudPermisoEnviadoMapper.toSolicitudPermisoPageResponse(pageResult, filter.idAccion());
    }
	
	// Llama al metodo que devuelve la lista de solicitudes de permisos enviados filtrados por las condiciones marcadas por los campos de la busqueda
	 private Page<SolicitudPermisoEntity> listaResultadosSolicitudPermisos(FiltroBandejaEnviadosFilter filter, int page, int size){
		 Specification<SolicitudPermisoEntity> spec = FiltroBandejaEnviadosSpecification.filterBy(filter);
	     Page<SolicitudPermisoEntity> pageResult = bandejaEnviadosRepository.findAll(spec , PageRequest.of(page, size));
	
	     return pageResult;
	}
	
	// Crea las opciones de la lista de estados que no se encuentran en BBDD
	private List<EstadoTramiteComboDTO> getListaConstantesEstado (){
		
		final List<EstadoTramiteComboDTO> estados = new ArrayList<EstadoTramiteComboDTO>();
		
		EstadoTramiteComboDTO estadoTodas = new EstadoTramiteComboDTO(Constantes.VALOR_TODAS, Constantes.TODAS);
		estados.add(estadoTodas);
		
		EstadoTramiteComboDTO estadoEnProceso = new EstadoTramiteComboDTO(Constantes.VALOR_EN_PROCESO, Constantes.EN_PROCESO);
		estados.add(estadoEnProceso);
		
		return estados;
	}
	
	// Crea las opciones de la lista de acciones de la pantalla
	private List<EstadoTramiteComboDTO> getListaConstantesAccion (){
		
		final List<EstadoTramiteComboDTO> acciones = new ArrayList<EstadoTramiteComboDTO>();
		
		EstadoTramiteComboDTO accionCreada = new EstadoTramiteComboDTO(Constantes.VALOR_CREADAS_POR_MI, Constantes.CREADAS_POR_MI);
		acciones.add(accionCreada);
		
		EstadoTramiteComboDTO accionValidada = new EstadoTramiteComboDTO(Constantes.VALOR_VALIDADAS_POR_MI, Constantes.VALIDADAS_POR_MI);
		acciones.add(accionValidada);
		
		EstadoTramiteComboDTO accionAutorizada = new EstadoTramiteComboDTO(Constantes.VALOR_AUTORIZADAS_POR_MI, Constantes.AUTORIZADAS_POR_MI);
		acciones.add(accionAutorizada);
		
		return acciones;
	}
	
	// Devuelve la lista de solicitantes de solicitudes de permisos enviados filtrados por las condiciones marcadas por los campos de la busqueda
	public List<SolicitanteComboDTO> getListaSolicitantes (FiltroBandejaEnviadosFilter filter, int page, int size){
		
		List<SolicitanteComboDTO> listaSolicitantesComboDTO = new ArrayList<SolicitanteComboDTO>();
		
		Page<SolicitudPermisoEntity> listaSolicitudPermisoEntity = listaResultadosSolicitudPermisos(filter,  page,  size);
		
		for (SolicitudPermisoEntity solicitudPermiso: listaSolicitudPermisoEntity) {
			
			UsuarioEntity solicitanteEntity = null;
			
			if(solicitudPermiso != null && solicitudPermiso.getSolicitante() != null) {
				solicitanteEntity = solicitudPermiso.getSolicitante();
				
				listaSolicitantesComboDTO.add(usuarioMapper.toSolicitanteComboDTO(solicitanteEntity));
			}	
		}
		
		return listaSolicitantesComboDTO;
	}
	
}
