package es.sgad.trama.permiso.mapper.bandejaPendientes;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.PermisoSearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaPendientes.SolicitudPermisoDetallePendienteDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.SolicitudPermisoPendienteDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.criteria.FiltroBandejaPendientesDTOSearchCriteria;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.tipo.TipoPermisoComboDTO;
import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.tipoOperacion.TipoOperacionMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.bandejaPendientes.FiltroBandejaPendientesFilter;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;

/**
 * Mapeador entre {@link SolicitudPermisoDetallePendienteDTO} y
 * {@link SolicitudPermisoEntity}.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class,TipoOperacionMapper.class})
public interface SolicitudPermisoPendienteMapper extends TramaMapper<SolicitudPermisoDetallePendienteDTO, SolicitudPermisoEntity> {

	default List <SolicitudPermisoDetallePendienteDTO> toSolicitudPermisoPendienteDTO(List <SolicitudPermisoEntity> listaSolicitudesPermisoEntity) {
        
        List<SolicitudPermisoDetallePendienteDTO> listaSolicitudesPermisoDetallePendienteDTO = new ArrayList<SolicitudPermisoDetallePendienteDTO>();
        SolicitudPermisoDetallePendienteDTO solicitudPermisoDetallePendienteDTO;
        TipoOperacionComboDTO tipoOperacion;
        TipoPermisoComboDTO tipoPermiso;
        EstadoTramiteComboDTO estado;
        
        for (SolicitudPermisoEntity SolicitudPermisoEntity: listaSolicitudesPermisoEntity) {
            
            List<SolicitudPermisoDetalleEntity> list = SolicitudPermisoEntity.getListaSolicitudPermisoDetalle();
            
            for(SolicitudPermisoDetalleEntity solicitudPermisoDetalle :list) {
            	
            	solicitudPermisoDetallePendienteDTO = new SolicitudPermisoDetallePendienteDTO();
            	tipoOperacion = new TipoOperacionComboDTO();
            	tipoPermiso = new TipoPermisoComboDTO();
            	estado = new EstadoTramiteComboDTO();
            	
            	tipoPermiso.setId(SolicitudPermisoEntity.getTipoPermisoAmbito().getTipoPermiso().getId());
            	tipoPermiso.setDescripcion(SolicitudPermisoEntity.getTipoPermisoAmbito().getDescripcion());
                solicitudPermisoDetallePendienteDTO.setTipoPermiso(tipoPermiso);
                
                tipoOperacion.setId(SolicitudPermisoEntity.getTipoOperacion().getId());
                tipoOperacion.setDescripcion(SolicitudPermisoEntity.getTipoOperacion().getDescripcion());
                solicitudPermisoDetallePendienteDTO.setTipoOperacion(tipoOperacion);
                
                solicitudPermisoDetallePendienteDTO.setFechaSolicitud(SolicitudPermisoEntity.getFechaSolicitud());
                
                estado.setId(SolicitudPermisoEntity.getEstadoTramite().getId());
                estado.setDescripcion(SolicitudPermisoEntity.getEstadoTramite().getDescripcion());
                solicitudPermisoDetallePendienteDTO.setEstado(estado);
                
            	solicitudPermisoDetallePendienteDTO.setFechaInicio(solicitudPermisoDetalle.getFechaInicio());
            	solicitudPermisoDetallePendienteDTO.setFechaInicio(solicitudPermisoDetalle.getFechaFin());
            	
            	solicitudPermisoDetallePendienteDTO.setHoraInicio(solicitudPermisoDetalle.getHoraInicio()); 
            	solicitudPermisoDetallePendienteDTO.setHoraFin(solicitudPermisoDetalle.getHoraFin());
            	
            	listaSolicitudesPermisoDetallePendienteDTO.add(solicitudPermisoDetallePendienteDTO);
            }
            
        }
        
        return listaSolicitudesPermisoDetallePendienteDTO;
    };
	

        
    default List <SolicitudPermisoPendienteDTO> toListaSolicitudPermisoPendienteDTO(List<SolicitudPermisoEntity> listaSolicitudesPermisoPendiente) {
    	
    	List <SolicitudPermisoPendienteDTO> listSolicitudPermisoPendienteDTO =  new ArrayList<>();
    	
	    for(SolicitudPermisoEntity solicitudPermisoPendiente: listaSolicitudesPermisoPendiente) {
	    	
	    	SolicitudPermisoPendienteDTO solicitudPermisoPendienteConLista = new SolicitudPermisoPendienteDTO();
			
	    	TipoPermisoComboDTO tipoPermisoComboDTO = new TipoPermisoComboDTO();
			
			if(!solicitudPermisoPendiente.getId().toString().isBlank()){
				solicitudPermisoPendienteConLista.setIdSolicitudPermiso(solicitudPermisoPendiente.getId().toString());
			}
			
			if(solicitudPermisoPendiente.getTipoPermisoAmbito().getTipoPermiso() != null && solicitudPermisoPendiente.getTipoPermisoAmbito().getTipoPermiso().getId() != null){
				tipoPermisoComboDTO.setId(solicitudPermisoPendiente.getTipoPermisoAmbito().getTipoPermiso().getId());
				
			}
			if(solicitudPermisoPendiente.getTipoPermisoAmbito() != null && !solicitudPermisoPendiente.getTipoPermisoAmbito().getDescripcion().isBlank()){
				tipoPermisoComboDTO.setDescripcion(solicitudPermisoPendiente.getTipoPermisoAmbito().getDescripcion());
			}
			
			solicitudPermisoPendienteConLista.setTipoPermiso(tipoPermisoComboDTO);
			
			TipoOperacionComboDTO tipoOperacionComboDTO = new TipoOperacionComboDTO();
			
			if(solicitudPermisoPendiente.getTipoOperacion() != null && solicitudPermisoPendiente.getTipoOperacion().getId() != 0){
				tipoOperacionComboDTO.setId(solicitudPermisoPendiente.getTipoOperacion().getId());
			}
			if(solicitudPermisoPendiente.getTipoOperacion() != null && !solicitudPermisoPendiente.getTipoOperacion().getDescripcion().isBlank()){
				tipoOperacionComboDTO.setDescripcion(solicitudPermisoPendiente.getTipoOperacion().getDescripcion());
			}
			
			solicitudPermisoPendienteConLista.setTipoOperacion(tipoOperacionComboDTO);
			
			EstadoTramiteComboDTO estadoTramiteComboDTO = new EstadoTramiteComboDTO();
			
			if(solicitudPermisoPendiente.getEstadoTramite() != null && solicitudPermisoPendiente.getEstadoTramite().getId() != 0){
				estadoTramiteComboDTO.setId(solicitudPermisoPendiente.getEstadoTramite().getId());
			}
			if(solicitudPermisoPendiente.getEstadoTramite() != null && !solicitudPermisoPendiente.getEstadoTramite().getDescripcion().isBlank()){
				estadoTramiteComboDTO.setDescripcion(solicitudPermisoPendiente.getEstadoTramite().getDescripcion());
			}
			
			solicitudPermisoPendienteConLista.setEstado(estadoTramiteComboDTO);
			
			if(solicitudPermisoPendiente.getFechaSolicitud() != null){
				solicitudPermisoPendienteConLista.setFechaSolicitud(solicitudPermisoPendiente.getFechaSolicitud());
			}
			List<SolicitudPermisoDetalleDTO> listaSolicitudesPermisoDetalleDTO = new ArrayList<SolicitudPermisoDetalleDTO>();
			
			for(SolicitudPermisoDetalleEntity solicitudPermisoDetalle: solicitudPermisoPendiente.getListaSolicitudPermisoDetalle()) {
			
				SolicitudPermisoDetalleDTO solicitudPermisoDetalleDTO = new SolicitudPermisoDetalleDTO();
				
				if(solicitudPermisoDetalle.getFechaInicio() != null){
					solicitudPermisoDetalleDTO.setFechaInicio(solicitudPermisoDetalle.getFechaInicio());
				}
				if(solicitudPermisoDetalle.getFechaFin() != null){
					solicitudPermisoDetalleDTO.setFechaFin(solicitudPermisoDetalle.getFechaFin());
				}				
				if(solicitudPermisoDetalle.getHoraInicio() != null){
					solicitudPermisoDetalleDTO.setHoraInicio(solicitudPermisoDetalle.getHoraInicio());
				}
				if(solicitudPermisoDetalle.getHoraFin() != null){
					solicitudPermisoDetalleDTO.setHoraFin(solicitudPermisoDetalle.getHoraFin());
				}
				
				if(solicitudPermisoDetalle.getObservaciones() != null){
					solicitudPermisoDetalleDTO.setObservaciones(solicitudPermisoDetalle.getObservaciones());
				}
				
				listaSolicitudesPermisoDetalleDTO.add(solicitudPermisoDetalleDTO);
			}
			
			solicitudPermisoPendienteConLista.setListaSolicitudPermisoDetalle(listaSolicitudesPermisoDetalleDTO);
			
			listSolicitudPermisoPendienteDTO.add(solicitudPermisoPendienteConLista);
			
		}
	    
	    return listSolicitudPermisoPendienteDTO;
    }

    FiltroBandejaPendientesFilter toFilter(FiltroBandejaPendientesDTOSearchCriteria searchCriteria);

	default PermisoSearchPageResponse<SolicitudPermisoPendienteDTO> toSolicitudPermisoPageResponse(
			Page<SolicitudPermisoEntity> page) {
		PermisoSearchPageResponse<SolicitudPermisoPendienteDTO> pageResponse = new PermisoSearchPageResponse<>();
		pageResponse.setContent(toListaSolicitudPermisoPendienteDTO(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
