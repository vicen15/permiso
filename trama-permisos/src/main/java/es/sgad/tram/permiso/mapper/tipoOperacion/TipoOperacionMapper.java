package es.sgad.trama.permiso.mapper.tipoOperacion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.solicitudPermiso.SolicitudPermisoMapper;
import es.sgad.trama.permiso.persistance.entity.tipoOperacion.TipoOperacionEntity;

@Mapper(componentModel = "spring" , uses = {SolicitudPermisoMapper.class})
public interface TipoOperacionMapper extends TramaMapper<TipoOperacionDTO, TipoOperacionEntity>{
	
	@Mappings({
		@Mapping(target= "listaSolicitudPermisos" , source ="listaSolicitudPermisos")
	})
	TipoOperacionDTO toDomain(TipoOperacionEntity entity);
	 
	  
	  @Mappings({
		  @Mapping(target= "listaSolicitudPermisos" , source ="listaSolicitudPermisos")
		  
	  })
	  TipoOperacionEntity toEntity(TipoOperacionDTO dto);
}
