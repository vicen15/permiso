package es.sgad.trama.permiso.mapper.error;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.error.ErrorLogEntity;

/**
 * Mapeador entre {@link ErrorLogDTO} y {@link ErrorLogEntity}.
 */
@Mapper(componentModel = "spring", uses = {ErrorValidacionMapper.class, UsuarioMapperHelper.class})
public interface ErrorLogMapper extends TramaMapper<ErrorLogDTO, ErrorLogEntity> { 
	
		@Mappings({
			@Mapping(target= "listaValidaciones" , source ="listaValidaciones"),
			@Mapping(target= "usuario" , source ="usuario.id")
		})
		ErrorLogDTO toDomain(ErrorLogEntity entity);
	 
		
		@Mappings({
			@Mapping(target= "listaValidaciones" , source ="listaValidaciones"),		 
			@Mapping(target = "usuario", source = "usuario", qualifiedByName = "mapUsuarioId")
		})
		ErrorLogEntity toEntity(ErrorLogDTO dto);

}
