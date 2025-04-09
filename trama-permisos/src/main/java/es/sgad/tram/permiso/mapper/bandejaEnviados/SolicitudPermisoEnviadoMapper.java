package es.sgad.trama.permiso.mapper.bandejaEnviados;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoDetalleEnviadoDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoEnviadoDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.criteria.FiltroBandejaEnviadosDTOSearchCriteria;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.tipoOperacion.TipoOperacionMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosFilter;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;

/**
 * Mapeador entre {@link SolicitudPermisoDetalleEnviadoDTO} y
 * {@link SolicitudPermisoEntity}.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class,TipoOperacionMapper.class})
public interface SolicitudPermisoEnviadoMapper extends TramaMapper<SolicitudPermisoDetalleEnviadoDTO, SolicitudPermisoEntity> {

	default List <SolicitudPermisoDetalleEnviadoDTO> toSolicitudPermisoEnviadoDTO(List <SolicitudPermisoEntity> listaSolicitudesPermisoEntity) {
        
        List<SolicitudPermisoDetalleEnviadoDTO> listaSolicitudesPermisoDetalleEnviadaDTO = new ArrayList<SolicitudPermisoDetalleEnviadoDTO>();
        SolicitudPermisoDetalleEnviadoDTO solicitudPermisoDetalleEnviadaDTO;
        TipoOperacionComboDTO tipoOperacion;
        TipoPermisoAmbitoComboDTO tipoPermisoAmbito;
        EstadoTramiteComboDTO estado;
        
        for (SolicitudPermisoEntity solicitudPermisoEntity: listaSolicitudesPermisoEntity) {
        	
        	 List<SolicitudPermisoDetalleEntity> list = solicitudPermisoEntity.getListaSolicitudPermisoDetalle();
        	 
             for(SolicitudPermisoDetalleEntity solicitudPermisoDetalle :list) {   
            	 
	        	solicitudPermisoDetalleEnviadaDTO = new SolicitudPermisoDetalleEnviadoDTO();
	        	tipoOperacion = new TipoOperacionComboDTO();
	            tipoPermisoAmbito = new TipoPermisoAmbitoComboDTO();
	            estado = new EstadoTramiteComboDTO();
	        	
	        	// tipoPermiso
	            tipoPermisoAmbito.setId(solicitudPermisoEntity.getTipoPermisoAmbito().getId());
	            tipoPermisoAmbito.setDescripcion(solicitudPermisoEntity.getTipoPermisoAmbito().getDescripcion());
	            solicitudPermisoDetalleEnviadaDTO.setTipoPermisoAmbito(tipoPermisoAmbito);
	            
	            // tipoOperacion
	            tipoOperacion.setId(solicitudPermisoEntity.getTipoOperacion().getId());
	            tipoOperacion.setDescripcion(solicitudPermisoEntity.getTipoOperacion().getDescripcion());
	            solicitudPermisoDetalleEnviadaDTO.setTipoOperacion(tipoOperacion);
	            
	            // fechaSolicitud
	            solicitudPermisoDetalleEnviadaDTO.setFechaSolicitud(solicitudPermisoEntity.getFechaSolicitud());
	            
	            // estado
	            estado.setId(solicitudPermisoEntity.getEstadoTramite().getId());
	            estado.setDescripcion(solicitudPermisoEntity.getEstadoTramite().getDescripcion());
	            solicitudPermisoDetalleEnviadaDTO.setEstado(estado);
	           
	            // validador
	            if(solicitudPermisoEntity.getValidador() != null && !StringUtils.isBlank(solicitudPermisoEntity.getValidador().getId().toString())){
					
					String nombreValidador = solicitudPermisoEntity.getValidador().getApellido1().concat(" ")
							.concat(solicitudPermisoEntity.getValidador().getApellido2()).concat(", ")
							.concat(solicitudPermisoEntity.getValidador().getNombre());
					solicitudPermisoDetalleEnviadaDTO.setNombreValidador(nombreValidador);
					
				}else {
					solicitudPermisoDetalleEnviadaDTO.setNombreValidador("");
				}
			
	            // autorizador
				if(solicitudPermisoEntity.getAutorizador() != null && !StringUtils.isBlank(solicitudPermisoEntity.getAutorizador().getId().toString())){
					
					String nombreAutorizador = solicitudPermisoEntity.getAutorizador().getApellido1().concat(" ")
							.concat(solicitudPermisoEntity.getAutorizador().getApellido2()).concat(", ")
							.concat(solicitudPermisoEntity.getAutorizador().getNombre());
					solicitudPermisoDetalleEnviadaDTO.setNombreAutorizador(nombreAutorizador);
					
				}else {
					solicitudPermisoDetalleEnviadaDTO.setNombreAutorizador("");
				}
	            
	            // Datos del detalle
	            // fecha inicio
				// fecha fin
	            // hora_inicio
	            // hora_fin
            	solicitudPermisoDetalleEnviadaDTO.setFechaInicio(solicitudPermisoDetalle.getFechaInicio());
            	solicitudPermisoDetalleEnviadaDTO.setFechaFin(solicitudPermisoDetalle.getFechaFin());
            	solicitudPermisoDetalleEnviadaDTO.setHoraInicio(solicitudPermisoDetalle.getHoraInicio()); 
            	solicitudPermisoDetalleEnviadaDTO.setHoraFin(solicitudPermisoDetalle.getHoraFin());
            	
            	solicitudPermisoDetalleEnviadaDTO.setObservaciones(solicitudPermisoDetalle.getObservaciones());
            	
            	// Añadir a la lista los detalles de permiso.
            	listaSolicitudesPermisoDetalleEnviadaDTO.add(solicitudPermisoDetalleEnviadaDTO);
            }
        	
        }
        
        return listaSolicitudesPermisoDetalleEnviadaDTO;
    };
	
    List<SolicitudPermisoEnviadoDTO> toSolicitudPermisoDetalleDtos(List<SolicitudPermisoEntity> list);
    
    default List <SolicitudPermisoEnviadoDTO> toListaSolicitudPermisoEnviadoDTO(List<SolicitudPermisoEntity> listaSolicitudesPermisoEnviado, String pIdAccion) {
    	
    	List<SolicitudPermisoEnviadoDTO> listaSolicitudesPermisoDetalleEnviada =  new ArrayList<SolicitudPermisoEnviadoDTO>();
   
	    for(SolicitudPermisoEntity solicitudPermisoEnviado: listaSolicitudesPermisoEnviado) {
			
	    	SolicitudPermisoEnviadoDTO solicitudPermisoEnviadoConLista = new SolicitudPermisoEnviadoDTO();
			
			TipoPermisoAmbitoComboDTO tipoPermisoAmbitoComboDTO = new TipoPermisoAmbitoComboDTO();
			
			if(!StringUtils.isBlank(solicitudPermisoEnviado.getId().toString())){
				solicitudPermisoEnviadoConLista.setIdSolicitudPermiso(solicitudPermisoEnviado.getId().toString());
			}
			
			if(!StringUtils.isBlank(solicitudPermisoEnviado.getTipoPermisoAmbito().getId().toString())){
				tipoPermisoAmbitoComboDTO.setId(solicitudPermisoEnviado.getTipoPermisoAmbito().getId());
				
			}
			if(!StringUtils.isBlank(solicitudPermisoEnviado.getTipoPermisoAmbito().getDescripcion())){
				tipoPermisoAmbitoComboDTO.setDescripcion(solicitudPermisoEnviado.getTipoPermisoAmbito().getDescripcion());
			}
			
			solicitudPermisoEnviadoConLista.setTipoPermisoAmbito(tipoPermisoAmbitoComboDTO);
			
			TipoOperacionComboDTO tipoOperacionComboDTO = new TipoOperacionComboDTO();
			
			if(solicitudPermisoEnviado.getTipoOperacion() != null && solicitudPermisoEnviado.getTipoOperacion().getId() != 0){
				tipoOperacionComboDTO.setId(solicitudPermisoEnviado.getTipoOperacion().getId());
			}
			if(!StringUtils.isBlank(solicitudPermisoEnviado.getTipoOperacion().getDescripcion())){
				tipoOperacionComboDTO.setDescripcion(solicitudPermisoEnviado.getTipoOperacion().getDescripcion());
			}
			
			solicitudPermisoEnviadoConLista.setTipoOperacion(tipoOperacionComboDTO);
			
			EstadoTramiteComboDTO estadoTramiteComboDTO = new EstadoTramiteComboDTO();
			
			if(solicitudPermisoEnviado.getEstadoTramite() != null && solicitudPermisoEnviado.getEstadoTramite().getId() != 0){
				estadoTramiteComboDTO.setId(solicitudPermisoEnviado.getEstadoTramite().getId());
			}
			if(!StringUtils.isBlank(solicitudPermisoEnviado.getEstadoTramite().getDescripcion())){
				estadoTramiteComboDTO.setDescripcion(solicitudPermisoEnviado.getEstadoTramite().getDescripcion());
			}
			
			solicitudPermisoEnviadoConLista.setEstado(estadoTramiteComboDTO);
			
			if(pIdAccion != null && pIdAccion.equals(Constantes.VALOR_STRING_CREADAS_POR_MI)) {
			
				if(solicitudPermisoEnviado.getValidador() != null && !StringUtils.isBlank(solicitudPermisoEnviado.getValidador().getId().toString())){
					
					String nombreValidador = solicitudPermisoEnviado.getValidador().getApellido1().concat(" ")
							.concat(solicitudPermisoEnviado.getValidador().getApellido2()).concat(", ")
							.concat(solicitudPermisoEnviado.getValidador().getNombre());
					solicitudPermisoEnviadoConLista.setNombreValidador(nombreValidador);
					
				}else {
					solicitudPermisoEnviadoConLista.setNombreValidador("");
				}
			
				if(solicitudPermisoEnviado.getAutorizador() != null && !StringUtils.isBlank(solicitudPermisoEnviado.getAutorizador().getId().toString())){
					
					String nombreAutorizador = solicitudPermisoEnviado.getAutorizador().getApellido1().concat(" ")
							.concat(solicitudPermisoEnviado.getAutorizador().getApellido2()).concat(", ")
							.concat(solicitudPermisoEnviado.getAutorizador().getNombre());
					solicitudPermisoEnviadoConLista.setNombreAutorizador(nombreAutorizador);
					
				}else {
					solicitudPermisoEnviadoConLista.setNombreAutorizador("");
				}
			
			}
			
			if(pIdAccion != null && pIdAccion.equals(Constantes.VALOR_STRING_VALIDADAS_POR_MI)) {
				
				if(solicitudPermisoEnviado.getSolicitante() != null && !StringUtils.isBlank(solicitudPermisoEnviado.getSolicitante().getId().toString())){
					
					String nombreSolicitante = solicitudPermisoEnviado.getSolicitante().getApellido1().concat(" ")
							.concat(solicitudPermisoEnviado.getSolicitante().getApellido2()).concat(", ")
							.concat(solicitudPermisoEnviado.getSolicitante().getNombre());
					solicitudPermisoEnviadoConLista.setNombreSolicitante(nombreSolicitante);
					
				}
			
				if(solicitudPermisoEnviado.getAutorizador() != null && !StringUtils.isBlank(solicitudPermisoEnviado.getAutorizador().getId().toString())){
					
					String nombreAutorizador = solicitudPermisoEnviado.getAutorizador().getApellido1().concat(" ")
							.concat(solicitudPermisoEnviado.getAutorizador().getApellido2()).concat(", ")
							.concat(solicitudPermisoEnviado.getAutorizador().getNombre());
					solicitudPermisoEnviadoConLista.setNombreAutorizador(nombreAutorizador);
					
				}else {
					solicitudPermisoEnviadoConLista.setNombreAutorizador("");
				}
			
			}
			
			if(pIdAccion != null && pIdAccion.equals(Constantes.VALOR_STRING_AUTORIZADAS_POR_MI)) {
				
				if(solicitudPermisoEnviado.getSolicitante() != null && !StringUtils.isBlank(solicitudPermisoEnviado.getSolicitante().getId().toString())){
					
					String nombreSolicitante = solicitudPermisoEnviado.getSolicitante().getApellido1().concat(" ")
							.concat(solicitudPermisoEnviado.getSolicitante().getApellido2()).concat(", ")
							.concat(solicitudPermisoEnviado.getSolicitante().getNombre());
					solicitudPermisoEnviadoConLista.setNombreSolicitante(nombreSolicitante);
					
				}
			
				if(solicitudPermisoEnviado.getValidador() != null && !StringUtils.isBlank(solicitudPermisoEnviado.getValidador().getId().toString())){
					
					String nombreValidador = solicitudPermisoEnviado.getValidador().getApellido1().concat(" ")
							.concat(solicitudPermisoEnviado.getValidador().getApellido2()).concat(", ")
							.concat(solicitudPermisoEnviado.getValidador().getNombre());
					solicitudPermisoEnviadoConLista.setNombreValidador(nombreValidador);
					
				}else {
					solicitudPermisoEnviadoConLista.setNombreValidador("");
				}
			
			}
			
			if(solicitudPermisoEnviado.getFechaSolicitud() != null){
				solicitudPermisoEnviadoConLista.setFechaSolicitud(solicitudPermisoEnviado.getFechaSolicitud());
			}
			List<SolicitudPermisoDetalleDTO> listaSolicitudesPermisoDetalleDTO = new ArrayList<SolicitudPermisoDetalleDTO>();
			
			for(SolicitudPermisoDetalleEntity solicitudPermisoDetalle: solicitudPermisoEnviado.getListaSolicitudPermisoDetalle()) {
							
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
			
			solicitudPermisoEnviadoConLista.setListaSolicitudPermisoDetalle(listaSolicitudesPermisoDetalleDTO);
			
			listaSolicitudesPermisoDetalleEnviada.add(solicitudPermisoEnviadoConLista);
		}
	    
	    return listaSolicitudesPermisoDetalleEnviada;
    }

    FiltroBandejaEnviadosFilter toFilter(FiltroBandejaEnviadosDTOSearchCriteria searchCriteria);

	default SearchPageResponse<SolicitudPermisoEnviadoDTO> toSolicitudPermisoPageResponse(
			Page<SolicitudPermisoEntity> page, String pIdAccion) {
		SearchPageResponse<SolicitudPermisoEnviadoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toListaSolicitudPermisoEnviadoDTO(page.getContent(), pIdAccion));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
