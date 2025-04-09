package es.sgad.trama.permiso.mapper.solicitudPermisoDetalle;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.criteria.SolicitudPermisoDetalleSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;
import es.sgad.trama.permiso.persistance.solicitudPermisoDetalle.SolicitudPermisoDetalleFilter;

/**
 * Mapeador entre {@link SolicitudPermisoDetalleDTO} y
 * {@link SolicitudPermisoDetalleEntity}.
 */
@Mapper(componentModel = "spring")
public interface SolicitudPermisoDetalleMapper
		extends TramaMapper<SolicitudPermisoDetalleDTO, SolicitudPermisoDetalleEntity> {

	@Mappings({ @Mapping(target = "idSolicitudPermiso", source = "solicitudPermiso.id") })
	SolicitudPermisoDetalleDTO toDomain(SolicitudPermisoDetalleEntity solicitudPermisoDetalleEntity);

	@Mappings({ @Mapping(target = "solicitudPermiso.id", source = "idSolicitudPermiso") })
	SolicitudPermisoDetalleEntity toEntity(SolicitudPermisoDetalleDTO solicitudPermisoDetalleDTO);
	
	// para que pase del search criteria al filtro
	SolicitudPermisoDetalleFilter toFilter(SolicitudPermisoDetalleSearchCriteria searchCriteria);

	List<SolicitudPermisoDetalleDTO> toSolicitudPermisoDetalleDtos(List<SolicitudPermisoDetalleEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<SolicitudPermisoDetalleDTO> toSolicitudPermisoDetallePageResponse(
			Page<SolicitudPermisoDetalleEntity> page) {
		SearchPageResponse<SolicitudPermisoDetalleDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toSolicitudPermisoDetalleDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}
}

