package es.sgad.trama.permiso.mapper.usuario.usuarioEdificioCalendarioFestivo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.franja.FranjaEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoEntity;

/**
 * Mapeador entre {@link FranjaDTO} y {@link FranjaEntity}.
 */
@Mapper(componentModel = "spring")
public interface UsuarioEdificioCalendarioFestivoMapper extends TramaMapper <UsuarioEdificioCalendarioFestivoDTO, UsuarioEdificioCalendarioFestivoEntity>{

	@Mappings({
		  @Mapping(target= "idCalendarioFestivo" , source ="calendarioFestivo.id"),
		  @Mapping(target= "idUsuario" , source ="usuario.id")
		  
	  })
	UsuarioEdificioCalendarioFestivoDTO toDomain(UsuarioEdificioCalendarioFestivoEntity entity);
	 
	  
	  @Mappings({
		  @Mapping(target= "calendarioFestivo.id" , source ="idCalendarioFestivo"),
		  @Mapping(target= "usuario.id" , source ="idUsuario")
		  
	  })
	  UsuarioEdificioCalendarioFestivoEntity toEntity(UsuarioEdificioCalendarioFestivoDTO dto);
	
}
