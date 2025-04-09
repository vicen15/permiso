package es.sgad.trama.permiso.mapper.ambito;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.AmbitoDTO;
import es.sgad.trama.permiso.domain.ambito.criteria.AmbitoSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.calendario.calendarioFestivo.CalendarioFestivoMapper;
import es.sgad.trama.permiso.mapper.calendario.calendarioPatron.CalendarioPatronMapper;
import es.sgad.trama.permiso.mapper.edificio.EdificioMapper;
import es.sgad.trama.permiso.mapper.tipoHora.TipoHoraMapper;
import es.sgad.trama.permiso.mapper.turno.TurnoMapper;
import es.sgad.trama.permiso.persistance.ambito.AmbitoFilter;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;

/**
 * Mapeador entre {@link AmbitoDTO} y {@link AmbitoEntity}.
 */
@Mapper(componentModel = "spring", uses = { TurnoMapper.class, EdificioMapper.class, CalendarioPatronMapper.class,
		TipoHoraMapper.class, CalendarioFestivoMapper.class })
public interface AmbitoMapper extends TramaMapper<AmbitoDTO, AmbitoEntity> {

	@Mappings({ @Mapping(target = "listaCalendarioPatron", source = "listaCalendarioPatron"),
			@Mapping(target = "listaCalendarioFestivo", source = "listaCalendarioFestivo"),
			@Mapping(target = "listaEdificios", source = "listaEdificios"),
			@Mapping(target = "listaTurnos", source = "listaTurnos"),
			@Mapping(target = "listaTipoHoras", source = "listaTipoHoras")

	})
	AmbitoDTO toDomain(AmbitoEntity entity);

	@Mappings({ @Mapping(target = "listaCalendarioPatron", source = "listaCalendarioPatron"),
			@Mapping(target = "listaCalendarioFestivo", source = "listaCalendarioFestivo"),
			@Mapping(target = "listaEdificios", source = "listaEdificios"),
			@Mapping(target = "listaTurnos", source = "listaTurnos"),
			@Mapping(target = "listaTipoHoras", source = "listaTipoHoras")

	})
	AmbitoEntity toEntity(AmbitoDTO dto);

	// para que pase del search criteria al filtro
	AmbitoFilter toFilter(AmbitoSearchCriteria searchCriteria);

	List<AmbitoDTO> toAmbitoDTOs(List<AmbitoEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<AmbitoDTO> toAmbitoPageResponse(Page<AmbitoEntity> page) {
		SearchPageResponse<AmbitoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toAmbitoDTOs(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
