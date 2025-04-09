package es.sgad.trama.permiso.mapper.calendario.calendarioPatronDias;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.calendario.calendarioPatron.CalendarioPatronDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPatronDias.CalendarioPatronDiasDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPatronDias.criteria.CalendarioPatronDiasSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.calendario.calendarioPatronDias.CalendarioPatronDiasFilter;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron.CalendarioPatronEntity;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatronDias.CalendarioPatronDiasEntity;

/**
 * Mapeador entre {@link CalendarioPatronDTO} y {@link CalendarioPatronEntity}.
 */
@Mapper(componentModel = "spring")
public interface CalendarioPatronDiasMapper extends TramaMapper<CalendarioPatronDiasDTO, CalendarioPatronDiasEntity> {

	@Mappings({ @Mapping(target = "idCalendarioPatron", source = "calendarioPatron.id"),
			@Mapping(target = "idTurno", source = "turno.id")

	})
	CalendarioPatronDiasDTO toDomain(CalendarioPatronDiasEntity entity);

	@Mappings({ @Mapping(target = "calendarioPatron.id", source = "idCalendarioPatron"),
			@Mapping(target = "turno.id", source = "idTurno")

	})
	CalendarioPatronDiasEntity toEntity(CalendarioPatronDiasDTO dto);

	// para que pase del search criteria al filtro
	CalendarioPatronDiasFilter toFilter(CalendarioPatronDiasSearchCriteria searchCriteria);

	List<CalendarioPatronDiasDTO> toCalendarioPatronDiasDTOs(List<CalendarioPatronDiasEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<CalendarioPatronDiasDTO> toCalendarioPatronDiasPageResponse(
			Page<CalendarioPatronDiasEntity> page) {
		SearchPageResponse<CalendarioPatronDiasDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toCalendarioPatronDiasDTOs(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}
}
