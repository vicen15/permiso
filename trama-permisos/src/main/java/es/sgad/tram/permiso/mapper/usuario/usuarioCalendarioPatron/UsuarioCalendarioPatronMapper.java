package es.sgad.trama.permiso.mapper.usuario.usuarioCalendarioPatron;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.calendario.calendarioPatron.CalendarioPatronDTO;
import es.sgad.trama.permiso.domain.usuario.usuarioCalendarioPatron.UsuarioCalendarioPatronDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron.CalendarioPatronEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.usuarioCalendarioPatron.UsuarioCalendarioPatronEntity;

/**
 * Mapeador entre {@link CalendarioPatronDTO} y {@link CalendarioPatronEntity}.
 */
@Mapper(componentModel = "spring")
public interface UsuarioCalendarioPatronMapper extends TramaMapper<UsuarioCalendarioPatronDTO, UsuarioCalendarioPatronEntity> { 
	
	  @Mappings({
		  @Mapping(target= "idCalendarioPatron" , source ="calendarioPatron.id"),
		  @Mapping(target= "idUsuario" , source ="usuario.id")
		  
	  })
	  UsuarioCalendarioPatronDTO toDomain(UsuarioCalendarioPatronEntity entity);
	 
	  
	  @Mappings({
		  @Mapping(target= "calendarioPatron.id" , source ="idCalendarioPatron"),
		  @Mapping(target= "usuario.id" , source ="idUsuario")
		  
	  })
	  UsuarioCalendarioPatronEntity toEntity(UsuarioCalendarioPatronDTO dto);

}
