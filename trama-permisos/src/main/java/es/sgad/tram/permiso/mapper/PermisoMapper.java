package es.sgad.trama.permiso.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.PermisoDTO;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.criteria.PermisoSearchCriteria;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.PermisoFilter;
import es.sgad.trama.permiso.persistance.entity.PermisoEntity;

/**
 * Mapeador entre {@link PermisoDTO} y {@link PermisoEntity}.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface PermisoMapper extends TramaMapper<PermisoDTO, PermisoEntity> {

	@Mappings({
		@Mapping(target = "idUsuarioSolicitante", source="usuario.id")})
	PermisoDTO toDomain(PermisoEntity permisoEntity);

	@Mappings({
		@Mapping(target = "usuario.id", source="idUsuarioSolicitante")})
	PermisoEntity toEntity(PermisoDTO permisoDTO);

	PermisoFilter toFilter(PermisoSearchCriteria searchCriteria);

	List<PermisoDTO> toPermisosDtos(List<PermisoEntity> list);

	default SearchPageResponse<PermisoDTO> toPermisosPageResponse(
			Page<PermisoEntity> page) {
		SearchPageResponse<PermisoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toPermisosDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
