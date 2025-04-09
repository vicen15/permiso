package es.sgad.trama.permiso.mapper.calendario.calendarioFestivoDias;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.calendario.calendarioFestivoDias.CalendarioFestivoDiasDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioFestivoDias.criteria.CalendarioFestivoDiasSearchCriteria;
import es.sgad.trama.permiso.domain.franja.FranjaDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.calendario.calendarioFestivoDias.CalendarioFestivoDiasFilter;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivoDias.CalendarioFestivoDiasEntity;
import es.sgad.trama.permiso.persistance.entity.franja.FranjaEntity;

/**
 * Mapeador entre {@link FranjaDTO} y {@link FranjaEntity}.
 */
@Mapper(componentModel = "spring")
public interface CalendarioFestivoDiasMapper
		extends TramaMapper<CalendarioFestivoDiasDTO, CalendarioFestivoDiasEntity> {

	@Mappings({ @Mapping(target = "idCalendarioFestivo", source = "calendarioFestivo.id"),
			@Mapping(target = "idTurno", source = "turno.id")

	})
	CalendarioFestivoDiasDTO toDomain(CalendarioFestivoDiasEntity entity);

	@Mappings({ @Mapping(target = "calendarioFestivo.id", source = "idCalendarioFestivo"),
			@Mapping(target = "turno.id", source = "idTurno")

	})
	CalendarioFestivoDiasEntity toEntity(CalendarioFestivoDiasDTO dto);

	// para que pase del search criteria al filtro
	CalendarioFestivoDiasFilter toFilter(CalendarioFestivoDiasSearchCriteria searchCriteria);

	List<CalendarioFestivoDiasDTO> toCalendarioFestivoDiasDTOs(List<CalendarioFestivoDiasEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<CalendarioFestivoDiasDTO> toCalendarioFestivoDiasPageResponse(
			Page<CalendarioFestivoDiasEntity> page) {
		SearchPageResponse<CalendarioFestivoDiasDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toCalendarioFestivoDiasDTOs(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
