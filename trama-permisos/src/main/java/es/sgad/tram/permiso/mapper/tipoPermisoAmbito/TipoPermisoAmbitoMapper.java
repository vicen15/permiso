package es.sgad.trama.permiso.mapper.tipoPermisoAmbito;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoDTO;
import es.sgad.trama.permiso.domain.tipoPermisoAmbito.criteria.TipoPermisoAmbitoSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.ambito.AmbitoMapper;
import es.sgad.trama.permiso.mapper.tipo.TipoPermisoMapper;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoFilter;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;

/**
 * Mapeador entre {@link TipoPermisoAmbitoDTO} y {@link TipoPermisoAmbitoEntity}.
 */
@Mapper(componentModel = "spring", uses = { AmbitoMapper.class, TipoPermisoMapper.class })
public interface TipoPermisoAmbitoMapper extends TramaMapper<TipoPermisoAmbitoDTO, TipoPermisoAmbitoEntity>{
	@Mappings({ 
		@Mapping(target = "idTipoPermiso", source = "tipoPermiso.id"),
		@Mapping(target = "idAmbito", source = "ambito.id")})
	TipoPermisoAmbitoDTO toDomain(TipoPermisoAmbitoEntity entity);

	@Mappings({ 
		@Mapping(target = "tipoPermiso.id", source = "idTipoPermiso"),
		@Mapping(target = "ambito.id", source = "idAmbito")})
	TipoPermisoAmbitoEntity toEntity(TipoPermisoAmbitoDTO dto);

	// para que pase del search criteria al filtro
	TipoPermisoAmbitoFilter toFilter(TipoPermisoAmbitoSearchCriteria searchCriteria);

	List<TipoPermisoAmbitoDTO> toTipoPermisoAmbitoDtos(List<TipoPermisoAmbitoEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<TipoPermisoAmbitoDTO> toTipoPermisoPageResponse(Page<TipoPermisoAmbitoEntity> page) {
		SearchPageResponse<TipoPermisoAmbitoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toTipoPermisoAmbitoDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}
		
}
