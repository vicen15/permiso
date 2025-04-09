package es.sgad.trama.permiso.mapper.usuario.extras;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.usuario.extras.GrupoDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.extras.GrupoEntity;


/**
 * Mapeador entre {@link UsuarioDTO} y {@link UsuarioEntity}.
 */
@Mapper(componentModel = "spring" , uses = { UsuarioMapper.class} )
public interface GrupoMapper extends TramaMapper<GrupoDTO, GrupoEntity> { 
	
	@Mappings({
		
		  @Mapping(target= "idClasePersonal" , source ="clasePersonal.id"),
		  @Mapping(target= "listaUsuario" , source ="listaUsuario")
		  
	  })
	GrupoDTO toDomain(GrupoEntity entity);


@Mappings({

	  @Mapping(target= "clasePersonal.id" , source ="idClasePersonal"),
	  @Mapping(target= "listaUsuario" , source ="listaUsuario")
	  
})
GrupoEntity toEntity(GrupoDTO dto);

}
