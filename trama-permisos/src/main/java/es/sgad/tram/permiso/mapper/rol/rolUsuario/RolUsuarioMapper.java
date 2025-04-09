package es.sgad.trama.permiso.mapper.rol.rolUsuario;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.rol.rolUsuario.RolUsuarioDTO;
import es.sgad.trama.permiso.domain.rol.rolUsuario.criteria.RolUsuarioSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.rol.rolUsuario.RolUsuarioEntity;
import es.sgad.trama.permiso.persistance.rol.rolUsuario.RolUsuarioFilter;

/**
 * Mapeador entre {@link RolUsuarioDTO} y {@link RolUsuarioEntity}.
 */
@Mapper(componentModel = "spring" /*
									 * , uses = { TurnoMapper.class, EdificioMapper.class,
									 * CalendarioPatronMapper.class, TipoHoraMapper.class,
									 * CalendarioFestivoMapper.class}
									 */)
public interface RolUsuarioMapper extends TramaMapper<RolUsuarioDTO, RolUsuarioEntity> {

	@Mappings({ @Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "idUsuario", source = "usuario.id"), @Mapping(target = "idRol", source = "rol.id")

	})
	RolUsuarioDTO toDomain(RolUsuarioEntity entity);

	@Mappings({ @Mapping(target = "ambito.id", source = "idAmbito"),
			@Mapping(target = "usuario.id", source = "idUsuario"), @Mapping(target = "rol.id", source = "idRol") })
	RolUsuarioEntity toEntity(RolUsuarioDTO dto);

	// para que pase del search criteria al filtro
	RolUsuarioFilter toFilter(RolUsuarioSearchCriteria searchCriteria);

	List<RolUsuarioDTO> toRolUsuarioDtos(List<RolUsuarioEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<RolUsuarioDTO> toRolUsuarioPageResponse(Page<RolUsuarioEntity> page) {
		SearchPageResponse<RolUsuarioDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toRolUsuarioDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
