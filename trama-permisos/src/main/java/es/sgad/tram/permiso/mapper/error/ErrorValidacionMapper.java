package es.sgad.trama.permiso.mapper.error;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.error.ErrorValidacionDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.error.ErrorValidacionEntity;

/**
 * Mapeador entre {@link ErrorValidacionDTO} y {@link ErrorValidacionEntity}.
 */
@Mapper(componentModel = "spring", uses = UsuarioMapperHelper.class)
public interface ErrorValidacionMapper extends TramaMapper<ErrorValidacionDTO, ErrorValidacionEntity> { 
	
	@Mappings({
		  @Mapping(target= "errorId" , source ="errorLog.id"),
		  @Mapping(target= "usuario" , source ="usuario.id")
	  })
	ErrorValidacionDTO toDomain(ErrorValidacionEntity entity);
	
	
	@Mappings({
		  @Mapping(target= "errorLog.id" , source ="errorId"),		 
		  @Mapping(target = "usuario", source = "usuario", qualifiedByName = "mapUsuarioId")
	  })
	ErrorValidacionEntity toEntity(ErrorValidacionDTO dto);
	
}
