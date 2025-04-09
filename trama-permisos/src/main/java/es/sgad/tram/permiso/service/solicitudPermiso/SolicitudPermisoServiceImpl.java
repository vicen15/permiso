package es.sgad.trama.permiso.service.solicitudPermiso;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import es.sgad.trama.common.exception.DirectoryUploadNotFoundException;
import es.sgad.trama.common.exception.InternalErrorException;
import es.sgad.trama.common.exception.ItemNotFoundException;
import es.sgad.trama.common.file.FileUtil;
import es.sgad.trama.common.utils.SeguridadUtil;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoDTO;
import es.sgad.trama.permiso.mapper.solicitudPermiso.SolicitudPermisoMapper;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoRepository;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.tipoOperacion.TipoOperacionEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.estadoTramite.EstadoTramiteRepository;
import es.sgad.trama.permiso.persistance.firma.SolicitudPermisoFirmaRepository;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoFilter;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoRepository;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoSpecification;
import es.sgad.trama.permiso.persistance.tipoOperacion.TipoOperacionRepository;
import es.sgad.trama.permiso.persistance.usuario.UsuarioRepository;
import es.sgad.trama.permiso.service.fichero.FicheroService;
import es.sgad.trama.permiso.service.solicitudPermisoDetalle.SolicitudPermisoDetalleService;
import es.sgad.trama.permiso.service.solicitudPermisoDocumento.SolicitudPermisoDocumentoService;

@Service
public class SolicitudPermisoServiceImpl implements SolicitudPermisoService {

	@Autowired
	private SolicitudPermisoRepository solicitudPermisoRepository;

	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private EstadoTramiteRepository estadoTramiteRepository;

	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SolicitudPermisoFirmaRepository solicitudPermisoFirmaRepository;
	
	@Autowired
	private TipoPermisoAmbitoRepository tipoPermisoAmbitoRepository;
	
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private TipoOperacionRepository tipoOperacionRepository;

	@Autowired
	private SolicitudPermisoMapper solicitudPermisoMapper;	
	
	@Autowired
	private SolicitudPermisoDetalleService solicitudPermisoDetalleService;
	
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA	
	@Autowired
	private FicheroService ficheroService;
	
	@Autowired
	private SolicitudPermisoDocumentoService solicitudPermisoDocumentoService;

	
	@Override
    public SearchPageResponse<SolicitudPermisoDTO> searchSolicitudPermiso(SolicitudPermisoFilter filter, int page, int size) {
        Specification<SolicitudPermisoEntity> spec = SolicitudPermisoSpecification.filterBy(filter);
        Page<SolicitudPermisoEntity> pageResult = solicitudPermisoRepository.findAll(spec, PageRequest.of(page, size));
        return solicitudPermisoMapper.toSolicitudPermisoPageResponse(pageResult);
    }

	@Override
	@Transactional(rollbackFor = {Exception.class}
	)//Rollback si se producen errores
	public SolicitudPermisoDTO saveNuevaSolicitudPermiso(NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO,
			List<MultipartFile> archivos, BindingResult bindingResult) {

		SolicitudPermisoEntity nuevaSolicitudPermiso = cargarInformacionNuevaSolicitudPermiso(nuevaSolicitudPermisoDTO);
		
		String rutaFichero = null; //Variable para controlar el fichero creado
		List<String> rutaFicherosCreados = new ArrayList<>();//Variable para controlar los ficheros creados

		try {
			nuevaSolicitudPermiso = solicitudPermisoRepository.save(nuevaSolicitudPermiso);
				if(nuevaSolicitudPermiso != null) {
				
				//Insertamos detalles
				for(SolicitudPermisoDetalleDTO detalles : nuevaSolicitudPermisoDTO.getListaSolicitudPermisoDetalle()) {
					
					detalles.setIdSolicitudPermiso(nuevaSolicitudPermiso.getId());
					detalles.setObservaciones(SeguridadUtil.limpiarXSS(detalles.getObservaciones()));//limpiar contenido html inseguro
					solicitudPermisoDetalleService.saveSolicitudPermisoDetalle(detalles);
				}
				
				if(archivos != null) {
					//Insertamos ficheros
					for(MultipartFile archivo : archivos) {
						
						//Escribit en disco el archivo
						rutaFichero = ficheroService.guardarFichero(nuevaSolicitudPermiso.getFechaSolicitud(),nuevaSolicitudPermiso.getId().toString(), archivo);
						
						
						//recoger la ruta del archivo para guardarla y utilizar a posteriori para eliminar ficheros si ocurre error
						rutaFicherosCreados.add(rutaFichero);
						//Creamos solicitudPermisoDocumento
						SolicitudPermisoDocumentoDTO solicitudPermisoDocumentoDTO = SolicitudPermisoDocumentoDTO.builder()
								.idSolicitudPermiso(nuevaSolicitudPermiso.getId())
								.descripcion(null)
								.nombre(FileUtil.extraerNombreFicheroDesdeRutaAbsoluta(rutaFichero))
								.ruta(rutaFichero)
								.peso(FileUtil.calcularKilobytesDesdeBytes(archivo.getSize())).build();
						
						//insertamos en base de datos
						solicitudPermisoDocumentoDTO = solicitudPermisoDocumentoService.saveSolicitudPermisoDocumento(solicitudPermisoDocumentoDTO);
						
					}
				}
			}

		}catch(IOException  iOexception) {//Excepcion en el sistema de ficheros
			
			ficheroService.eliminarFichero(rutaFicherosCreados);
			throw new InternalErrorException(iOexception.getCause());
			
		}catch(JpaSystemException | GenericJDBCException dbException) { //Excepcion base de datos
			
			ficheroService.eliminarFichero(rutaFicherosCreados);
			throw new InternalErrorException(dbException.getCause());
			
		}catch(ItemNotFoundException itemNotFoundException) { //Excepcion al escribir archivos de la solicitud
			
			ficheroService.eliminarFichero(rutaFicherosCreados);
			throw new InternalErrorException(itemNotFoundException.getCause());
		}catch(DirectoryUploadNotFoundException directoryUploadNotFoundException) {//Excepcion no existe directorio ROOT uploads

			throw new DirectoryUploadNotFoundException(directoryUploadNotFoundException.getCause());
		}
		catch(Exception e) {//Excepcion generica
			
			ficheroService.eliminarFichero(rutaFicherosCreados);
			throw  new InternalErrorException(e.getCause());
		}
		
		//Devolvemos la solicitud permiso con todos los datos
		return this.getSolicitudPermisoById(nuevaSolicitudPermiso.getId().toString());
	}	

	
	//TODO Esta funcion deberia de establecerse en el mapper, pero, al tener relacionados objetos hijos en null,
	//el mapper genera el objeto con new y causa un detached entity exception
	/**
	 * Establece el nuevo objeto {@link SolicitudPermisoEntity} con los datos necesarios para crear permiso
	 * @param solicitudPermisoDTO
	 * @return SolicitudPermisoEntity con los datos necesarios para alta
	 */
	private SolicitudPermisoEntity cargarInformacionNuevaSolicitudPermiso(NuevaSolicitudPermisoDTO solicitudPermisoDTO) {
		SolicitudPermisoEntity solicitudPermisoEntity = new SolicitudPermisoEntity();
		
		UsuarioEntity usuarioEntity = usuarioRepository.findById(solicitudPermisoDTO.getIdSolicitante()).orElse(null);
		TipoPermisoAmbitoEntity tipoPermisoAmbitoEntity = tipoPermisoAmbitoRepository.findById(solicitudPermisoDTO.getIdTipoPermisoAmbito()).orElse(null);
		
		//TODO Enums o Constante para evitar realizar hardcode para tipo operacion 1 (Solicitud) y estado tramite 1 (Pendiente Validar)
		TipoOperacionEntity tipoOperacion = tipoOperacionRepository.findById(1L).orElse(null);//TipoOperacion solicitud
		
		solicitudPermisoEntity.setSolicitante(usuarioEntity);

		solicitudPermisoEntity.setValidador(null);
		solicitudPermisoEntity.setAutorizador(null);
		solicitudPermisoEntity.setGestorPersonal(null);
		solicitudPermisoEntity.setGestorPersonalAsignado(null);
		
		solicitudPermisoEntity.setTipoPermisoAmbito(tipoPermisoAmbitoEntity);
		
		solicitudPermisoEntity.setMotivoDenegacion(null);
		solicitudPermisoEntity.setFechaSolicitud(LocalDate.now());//se establece fecha solicitud
		solicitudPermisoEntity.setFechaValidacion(null);
		solicitudPermisoEntity.setFechaAutorizacion(null);
		solicitudPermisoEntity.setFechaGestorPersonal(null);
		solicitudPermisoEntity.setFechaDenegada(null);
		solicitudPermisoEntity.setFechaPeticionDocumentacion(null);
		
		solicitudPermisoEntity.setObservacionesValidador(null);
		solicitudPermisoEntity.setObservacionesAutorizador(null);
		solicitudPermisoEntity.setObservacionesParaUsuario(SeguridadUtil.limpiarXSS(solicitudPermisoDTO.getObservacionesParaUsuario()));//limpiar html inseguro
		solicitudPermisoEntity.setEjercicio(LocalDate.now().getYear());//anio actual;
		
		solicitudPermisoEntity.setEstadoTramite(estadoTramiteRepository.findById(solicitudPermisoDTO.getIdEstadoTramite()).orElse(null));
		
		solicitudPermisoEntity.setObservacionesGp(null);
		solicitudPermisoEntity.setInfoParaGp(null);

		solicitudPermisoEntity.setAmbito(usuarioEntity.getAmbito());
		solicitudPermisoEntity.setFirma(null);
		solicitudPermisoEntity.setTipoOperacion(tipoOperacion); //Se establece el valor solicitud
		solicitudPermisoEntity.setListaSolicitudPermisoDetalle(null);
		solicitudPermisoEntity.setListaSolicitudPermisoDocumento(null);
		
		return solicitudPermisoEntity;
	}


	@Override
	@Transactional(readOnly = true)
	public SolicitudPermisoDTO getSolicitudPermisoById(String id) {
		SolicitudPermisoEntity solicitudPermisoEntity = this.solicitudPermisoRepository.findById(id)
				.orElse(null);
		if (solicitudPermisoEntity != null) {
			if (solicitudPermisoEntity.getTipoPermisoAmbito() != null) {
				// This will trigger the lazy loading of the tipo permiso ambito entity
				solicitudPermisoEntity.getTipoPermisoAmbito().getId();
			}
			if (solicitudPermisoEntity.getSolicitante() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getSolicitante().getId();
			}
			if (solicitudPermisoEntity.getValidador() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getValidador().getId();
			}
			if (solicitudPermisoEntity.getAutorizador() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getAutorizador().getId();
			}
			if (solicitudPermisoEntity.getGestorPersonal() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getGestorPersonal().getId();
			}
			if (solicitudPermisoEntity.getGestorPersonalAsignado() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getGestorPersonalAsignado().getId();
			}
			if (solicitudPermisoEntity.getEstadoTramite() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getEstadoTramite().getId();
			}
			if (solicitudPermisoEntity.getAmbito() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoEntity.getAmbito().getId();
			}
//			if (solicitudPermisoEntity.getListaSolicitudPermisoDetalle() != null) {
//				// This will trigger the lazy loading of the huso horario entity
//				solicitudPermisoEntity.getListaSolicitudPermisoDetalle().forEach(t -> t.getId());
//			}
//			if (solicitudPermisoEntity.getListaSolicitudPermisoDocumento() != null) {
//				// This will trigger the lazy loading of the huso horario entity
//				solicitudPermisoEntity.getListaSolicitudPermisoDocumento().forEach(t -> t.getId());			
//			}
		}
		return this.solicitudPermisoMapper.toDomain(solicitudPermisoEntity);
	}
	
	
}
