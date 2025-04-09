package es.sgad.trama.permiso.mapper.tipo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.tipo.TipoPermisoDTO;
import es.sgad.trama.permiso.domain.tipo.criteria.TipoPermisoSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.solicitudPermiso.SolicitudPermisoMapper;
import es.sgad.trama.permiso.mapper.tipoDuracion.TipoDuracionMapper;
import es.sgad.trama.permiso.persistance.entity.tipo.TipoPermisoEntity;
import es.sgad.trama.permiso.persistance.tipo.TipoPermisoFilter;

/**
 * Mapeador entre {@link TipoPermisoDTO} y {@link TipoPermisoEntity}.
 */
@Mapper(componentModel = "spring", uses = { TipoDuracionMapper.class, SolicitudPermisoMapper.class })
public interface TipoPermisoMapper extends TramaMapper<TipoPermisoDTO, TipoPermisoEntity> {

	@Mappings({ @Mapping(target = "idTipoDuracion", source = "tipoDuracion.id")})
	TipoPermisoDTO toDomain(TipoPermisoEntity entity);

	@Mappings({ @Mapping(target = "tipoDuracion.id", source = "idTipoDuracion")})
	TipoPermisoEntity toEntity(TipoPermisoDTO dto);

	// para que pase del search criteria al filtro
	TipoPermisoFilter toFilter(TipoPermisoSearchCriteria searchCriteria);

	List<TipoPermisoDTO> toTipoPermisoDtos(List<TipoPermisoEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<TipoPermisoDTO> toTipoPermisoPageResponse(Page<TipoPermisoEntity> page) {
		SearchPageResponse<TipoPermisoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toTipoPermisoDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}
}