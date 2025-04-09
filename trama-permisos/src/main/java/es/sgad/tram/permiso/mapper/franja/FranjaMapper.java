package es.sgad.trama.permiso.mapper.franja;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.franja.FranjaDTO;
import es.sgad.trama.permiso.domain.franja.criteria.FranjaSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.persistance.entity.franja.FranjaEntity;
import es.sgad.trama.permiso.persistance.franja.FranjaFilter;

/**
 * Mapeador entre {@link FranjaDTO} y {@link FranjaEntity}.
 */
@Mapper(componentModel = "spring")
public interface FranjaMapper extends TramaMapper <FranjaDTO, FranjaEntity>{

	@Mappings({
		  @Mapping(target= "idTipoHora" , source ="tipoHora.id"),
		  @Mapping(target= "idTurno" , source ="turno.id")
		  
	  })
	  FranjaDTO toDomain(FranjaEntity entity);
	 
	  
	  @Mappings({
		  @Mapping(target= "tipoHora.id" , source ="idTipoHora"),
		  @Mapping(target= "turno.id" , source ="idTurno")
		  
	  })
	  FranjaEntity toEntity(FranjaDTO dto);
	  
	// para que pase del search criteria al filtro
		FranjaFilter toFilter(FranjaSearchCriteria searchCriteria);

		List<FranjaDTO> toFranjaDTOs(List<FranjaEntity> list);

		// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
		// hay entre <> por los que queremos.
		default SearchPageResponse<FranjaDTO> toFranjaPageResponse(Page<FranjaEntity> page) {
			SearchPageResponse<FranjaDTO> pageResponse = new SearchPageResponse<>();
			pageResponse.setContent(toFranjaDTOs(page.getContent()));
			pageResponse.setPage(page.getNumber());
			pageResponse.setSize(page.getSize());
			pageResponse.setTotalPages(page.getTotalPages());
			pageResponse.setTotalSize(page.getTotalElements());
			return pageResponse;
		}
}
