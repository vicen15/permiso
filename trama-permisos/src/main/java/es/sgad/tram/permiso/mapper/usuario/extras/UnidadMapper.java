package es.sgad.trama.permiso.mapper.usuario.extras;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.usuario.extras.UnidadDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.entity.usuario.extras.UnidadEntity;

/**
 * Mapeador entre {@link UnidadDTO} y {@link UnidadEntity}.
 */
@Mapper(componentModel = "spring"  , uses = { UsuarioMapper.class} )
public interface UnidadMapper extends TramaMapper<UnidadDTO, UnidadEntity> {
	@Mappings({
		  @Mapping(target= "idAmbito" , source ="ambito.id"),
		
		  @Mapping(target= "idUnidad" , source ="unidad.id"),
		  @Mapping(target= "idUnidadTipo" , source ="unidadTipo.id")
		  
	  })
	UnidadDTO toDomain(UnidadEntity entity);


@Mappings({
	 @Mapping(target= "ambito.id" , source ="idAmbito"),
	  
	  @Mapping(target= "unidad.id" , source ="idUnidad"),
	  @Mapping(target= "unidadTipo.id" , source ="idUnidadTipo"),
	
	  
})
UnidadEntity toEntity(UnidadDTO dto);

}
