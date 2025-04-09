package es.sgad.trama.permiso.mapper.turno;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.turno.TurnoDTO;
import es.sgad.trama.permiso.domain.turno.criteria.TurnoSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.calendario.calendarioFestivoDias.CalendarioFestivoDiasMapper;
import es.sgad.trama.permiso.mapper.calendario.calendarioPatronDias.CalendarioPatronDiasMapper;
import es.sgad.trama.permiso.mapper.franja.FranjaMapper;
import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import es.sgad.trama.permiso.persistance.turno.TurnoFilter;

/**
 * Mapeador entre {@link TurnoDTO} y {@link TurnoEntity}.
 */
@Mapper(componentModel = "spring", uses = { CalendarioPatronDiasMapper.class, CalendarioFestivoDiasMapper.class,
		FranjaMapper.class })
public interface TurnoMapper extends TramaMapper<TurnoDTO, TurnoEntity> {

	@Mappings({
			@Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "listaCalendarioPatronDias", source = "listaCalendarioPatronDias"),
			@Mapping(target = "listaFranjas", source = "listaFranjas"),
			@Mapping(target = "listaCalendarioFestivoDias", source = "listaCalendarioFestivoDias"),
			@Mapping(target = "listaCalendarioPersonalDias", source = "listaCalendarioPersonalDias"),
			@Mapping(target = "listaPausas", source = "listaPausas")

	})
	TurnoDTO toDomain(TurnoEntity entity);

	@Mappings({
			@Mapping(target = "ambito.id", source = "idAmbito"),
			@Mapping(target = "listaCalendarioPatronDias", source = "listaCalendarioPatronDias"),
			@Mapping(target = "listaFranjas", source = "listaFranjas"),
			@Mapping(target = "listaCalendarioFestivoDias", source = "listaCalendarioFestivoDias"),
			@Mapping(target = "listaCalendarioPersonalDias", source = "listaCalendarioPersonalDias"),
			@Mapping(target = "listaPausas", source = "listaPausas")

	})
	TurnoEntity toEntity(TurnoDTO dto);

	// para que pase del search criteria al filtro
	TurnoFilter toFilter(TurnoSearchCriteria searchCriteria);

	List<TurnoDTO> toTurnoDtos(List<TurnoEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<TurnoDTO> toTurnoPageResponse(Page<TurnoEntity> page) {
		SearchPageResponse<TurnoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toTurnoDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}
}
