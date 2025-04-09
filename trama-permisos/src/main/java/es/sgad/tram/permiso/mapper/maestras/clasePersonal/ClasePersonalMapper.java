package es.sgad.trama.permiso.mapper.maestras.clasePersonal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.maestras.clasePersonal.ClasePersonalDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.mapper.usuario.extras.GrupoMapper;
import es.sgad.trama.permiso.persistance.entity.maestras.clasePersonal.ClasePersonalEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;

/**
 * Mapeador entre {@link UsuarioDTO} y {@link UsuarioEntity}.
 */
@Mapper(componentModel = "spring", uses = { GrupoMapper.class, UsuarioMapper.class })
public interface ClasePersonalMapper extends TramaMapper<ClasePersonalDTO, ClasePersonalEntity> {

	@Mappings({ @Mapping(target = "listaGrupo", source = "listaGrupo"),
			@Mapping(target = "listaUsuario", source = "listaUsuario")

	})
	ClasePersonalDTO toDomain(ClasePersonalEntity entity);

	@Mappings({ @Mapping(target = "listaGrupo", source = "listaGrupo"),
			@Mapping(target = "listaUsuario", source = "listaUsuario") })
	ClasePersonalEntity toEntity(ClasePersonalDTO dto);

}
