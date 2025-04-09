package es.sgad.trama.permiso.mapper.calendario.calendarioFestivo;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.calendario.calendarioFestivo.CalendarioFestivoDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioFestivo.criteria.CalendarioFestivoSearchCriteria;
import es.sgad.trama.permiso.domain.franja.FranjaDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.calendario.calendarioFestivoDias.CalendarioFestivoDiasMapper;
import es.sgad.trama.permiso.persistance.calendario.calendarioFestivo.CalendarioFestivoFilter;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivo.CalendarioFestivoEntity;
import es.sgad.trama.permiso.persistance.entity.franja.FranjaEntity;

/**
 * Mapeador entre {@link FranjaDTO} y {@link FranjaEntity}.
 */
@Mapper(componentModel = "spring", uses = CalendarioFestivoDiasMapper.class)
public interface CalendarioFestivoMapper extends TramaMapper<CalendarioFestivoDTO, CalendarioFestivoEntity> {

	@Mappings({ @Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "idEdificio", source = "edificio.id"),
			@Mapping(target = "listaCalendarioFestivoDias", source = "listaCalendarioFestivoDias")

	})
	CalendarioFestivoDTO toDomain(CalendarioFestivoEntity entity);

	@Mappings({ @Mapping(target = "ambito.id", source = "idAmbito"),
			@Mapping(target = "edificio.id", source = "idEdificio")

	})
	CalendarioFestivoEntity toEntity(CalendarioFestivoDTO dto);

	// para que pase del search criteria al filtro
	CalendarioFestivoFilter toFilter(CalendarioFestivoSearchCriteria searchCriteria);

	List<CalendarioFestivoDTO> toCalendarioFestivoDTOs(List<CalendarioFestivoEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<CalendarioFestivoDTO> toCalendarioFestivoPageResponse(
			Page<CalendarioFestivoEntity> page) {
		SearchPageResponse<CalendarioFestivoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toCalendarioFestivoDTOs(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
