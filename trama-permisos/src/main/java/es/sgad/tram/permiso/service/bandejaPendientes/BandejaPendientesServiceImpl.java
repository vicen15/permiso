package es.sgad.trama.permiso.service.bandejaPendientes;

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
import es.sgad.trama.permiso.domain.bandejaPendientes.PantallaBandejaPendientesDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.SolicitudPermisoPendienteDTO;
import es.sgad.trama.permiso.domain.estadoTramite.AccionComboDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.mapper.bandejaPendientes.SolicitudPermisoPendienteMapper;
import es.sgad.trama.permiso.persistance.ambito.AmbitoRepository;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoRepository;
import es.sgad.trama.permiso.persistance.bandejaPendientes.FiltroBandejaPendientesFilter;
import es.sgad.trama.permiso.persistance.bandejaPendientes.FiltroBandejaPendientesRepository;
import es.sgad.trama.permiso.persistance.bandejaPendientes.FiltroBandejaPendientesSpecification;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.estadoTramite.EstadoTramiteRepository;

@Service("BandejaPermisoPendientesServiceImpl")
public class BandejaPendientesServiceImpl implements BandejaPendientesService {

	@Autowired
	private FiltroBandejaPendientesRepository bandejaPendientesRepository;

	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private AmbitoRepository ambitoRepository;

	@Autowired
	private TipoPermisoAmbitoRepository tipoPermisoAmbitoRepository;

//	@Autowired
//	private TipoOperacionRepository tipoOperacionRepository;
	
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private EstadoTramiteRepository estadoTramiteRepository;

	@Autowired
	private SolicitudPermisoPendienteMapper solicitudPermisoPendienteMapper;

	@Override
	public PantallaBandejaPendientesDTO getPantallaBandejaPendientes(String idUsuario, String idAmbito) {

		AmbitoEntity ambito = ambitoRepository.findById(idAmbito).orElse(null);

		List<TipoPermisoAmbitoComboDTO> listaTipoPermisoAmbito = this.tipoPermisoAmbitoRepository
				.findTipoPermisoAmbitoForCombo(ambito);

		List<SolicitanteComboDTO> listaSolicitante = this.bandejaPendientesRepository.findSolicitanteForCombo(idAmbito,
				idUsuario);

		//List<TipoOperacionComboDTO> listaTipoOperacion = this.tipoOperacionRepository.findTipoOperacionForCombo();
		
		List<AccionComboDTO> listaAccion = new ArrayList<>();
		listaAccion.add(new AccionComboDTO(Constantes.VALOR_TODAS, Constantes.TODAS));
		listaAccion.addAll(this.estadoTramiteRepository.findAccionForCombo());

		List<SolicitudPermisoEntity> listaSolicitudesPermiso = this.bandejaPendientesRepository
				.findListaSolicitudPermisoPendiente(idAmbito, idUsuario);

		List<SolicitudPermisoPendienteDTO> listaSolicitudesPermisoPendiente = solicitudPermisoPendienteMapper
				.toListaSolicitudPermisoPendienteDTO(listaSolicitudesPermiso);

		return PantallaBandejaPendientesDTO.builder().solicitanteCombo(listaSolicitante)
				.solicitudPermisoPendiente(listaSolicitudesPermisoPendiente)
				.accion(listaAccion)
				.tipoPermisoAmbitoCombo(listaTipoPermisoAmbito).build();
				//.tipoOperacionCombo(listaTipoOperacion).build();
	}

	@Override
	public PantallaBandejaPendientesDTO getPantallaBandejaPendientesError(String mensajeError) {

		PantallaBandejaPendientesDTO pantallaBandejaPendientesDTO = new PantallaBandejaPendientesDTO();

		return pantallaBandejaPendientesDTO;
	}

	@Override
	public SearchPageResponse<SolicitudPermisoPendienteDTO> searchBandejaPendientes(
			FiltroBandejaPendientesFilter filter, int page, int size) {
		Specification<SolicitudPermisoEntity> spec = FiltroBandejaPendientesSpecification.filterBy(filter);
		Page<SolicitudPermisoEntity> pageResult = bandejaPendientesRepository.findAll(spec, PageRequest.of(page, size));
		return solicitudPermisoPendienteMapper.toSolicitudPermisoPageResponse(pageResult);
	}
	
}
