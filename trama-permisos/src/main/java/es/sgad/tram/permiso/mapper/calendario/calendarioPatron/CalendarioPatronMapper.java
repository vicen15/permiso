package es.sgad.trama.permiso.mapper.calendario.calendarioPatron;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.calendario.calendarioPatron.CalendarioPatronDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPatron.criteria.CalendarioPatronSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.calendario.calendarioPatronDias.CalendarioPatronDiasMapper;
import es.sgad.trama.permiso.persistance.calendario.calendarioPatron.CalendarioPatronFilter;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron.CalendarioPatronEntity;

/**
 * Mapeador entre {@link CalendarioPatronDTO} y {@link CalendarioPatronEntity}.
 */
@Mapper(componentModel = "spring", uses = CalendarioPatronDiasMapper.class)
public interface CalendarioPatronMapper extends TramaMapper<CalendarioPatronDTO, CalendarioPatronEntity> {

	@Mappings({ @Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "listaCalendarioPatronDias", source = "listaCalendarioPatronDias")

	})
	CalendarioPatronDTO toDomain(CalendarioPatronEntity entity);

	@Mappings({ @Mapping(target = "ambito.id", source = "idAmbito")

	})
	CalendarioPatronEntity toEntity(CalendarioPatronDTO dto);

	// para que pase del search criteria al filtro
	CalendarioPatronFilter toFilter(CalendarioPatronSearchCriteria searchCriteria);

	List<CalendarioPatronDTO> toCalendarioPatronDTOs(List<CalendarioPatronEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<CalendarioPatronDTO> toCalendarioPatronPageResponse(Page<CalendarioPatronEntity> page) {
		SearchPageResponse<CalendarioPatronDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toCalendarioPatronDTOs(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
