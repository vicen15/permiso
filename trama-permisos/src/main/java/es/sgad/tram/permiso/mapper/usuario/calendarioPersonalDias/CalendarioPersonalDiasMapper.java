package es.sgad.trama.permiso.mapper.usuario.calendarioPersonalDias;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.calendario.calendarioPersonalDias.CalendarioPersonalDiasDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPersonalDias.criteria.CalendarioPersonalDiasSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.calendario.calendarioPersonalDias.CalendarioPersonalDiasFilter;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPersonalDias.CalendarioPersonalDiasEntity;

/**
 * Mapeador entre {@link CalendarioPersonalDTO} y {@link CalendarioPersonalEntity}.
 */
@Mapper(componentModel = "spring")
public interface CalendarioPersonalDiasMapper extends TramaMapper<CalendarioPersonalDiasDTO, CalendarioPersonalDiasEntity> { 
	
	  @Mappings({
		  @Mapping(target= "idUsuario" , source ="usuario.id"),
		  @Mapping(target= "idTurno" , source ="turno.id")
		  
	  })
	  CalendarioPersonalDiasDTO toDomain(CalendarioPersonalDiasEntity entity);
	 
	  
	  @Mappings({
		  @Mapping(target= "usuario.id" , source ="idUsuario"),
		  @Mapping(target= "turno.id" , source ="idTurno")
		  
	  })
	  CalendarioPersonalDiasEntity toEntity(CalendarioPersonalDiasDTO dto);

		// para que pase del search criteria al filtro
	  CalendarioPersonalDiasFilter toFilter(CalendarioPersonalDiasSearchCriteria searchCriteria);

		List<CalendarioPersonalDiasDTO> toCalendarioPersonalDiasDtos(List<CalendarioPersonalDiasEntity> list);

		// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
		// hay entre <> por los que queremos.
		default SearchPageResponse<CalendarioPersonalDiasDTO> toCalendarioPersonalDiasPageResponse(Page<CalendarioPersonalDiasEntity> page) {
			SearchPageResponse<CalendarioPersonalDiasDTO> pageResponse = new SearchPageResponse<>();
			pageResponse.setContent(toCalendarioPersonalDiasDtos(page.getContent()));
			pageResponse.setPage(page.getNumber());
			pageResponse.setSize(page.getSize());
			pageResponse.setTotalPages(page.getTotalPages());
			pageResponse.setTotalSize(page.getTotalElements());
			return pageResponse;
		}
}
