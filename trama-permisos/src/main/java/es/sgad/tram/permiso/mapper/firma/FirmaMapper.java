package es.sgad.trama.permiso.mapper.firma;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.firma.SolicitudPermisoFirmaDTO;
import es.sgad.trama.permiso.domain.firma.criteria.FirmaSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.solicitudPermiso.SolicitudPermisoMapper;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoFirma.SolicitudPermisoFirmaEntity;
import es.sgad.trama.permiso.persistance.firma.SolicitudPermisoFirmaFilter;

/**
 * Mapeador entre {@link SolicitudPermisoFirmaDTO} y {@link firmaEntity}.
 */
@Mapper(componentModel = "spring",  uses = { SolicitudPermisoMapper.class })
public interface FirmaMapper extends TramaMapper<SolicitudPermisoFirmaDTO, SolicitudPermisoFirmaEntity> {

	@Mappings({ @Mapping(target = "listaSolicitudPermiso", source = "listaSolicitudPermiso") })
	SolicitudPermisoFirmaDTO toDomain(SolicitudPermisoFirmaEntity entity);

	@Mappings({ @Mapping(target = "listaSolicitudPermiso", source = "listaSolicitudPermiso") })
	SolicitudPermisoFirmaEntity toEntity(SolicitudPermisoFirmaDTO dto);

	// para que pase del search criteria al filtro
	SolicitudPermisoFirmaFilter toFilter(FirmaSearchCriteria searchCriteria);

	List<SolicitudPermisoFirmaDTO> toSolicitudPermisoFirmaDtos(List<SolicitudPermisoFirmaEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<SolicitudPermisoFirmaDTO> toPermisoPageResponse(Page<SolicitudPermisoFirmaEntity> page) {
		SearchPageResponse<SolicitudPermisoFirmaDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toSolicitudPermisoFirmaDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
