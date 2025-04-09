package es.sgad.trama.permiso.mapper.edificio;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.edificio.EdificioDTO;
import es.sgad.trama.permiso.domain.edificio.criteria.EdificioSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.ambito.AmbitoMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.edificio.EdificioFilter;
import es.sgad.trama.permiso.persistance.entity.edificio.EdificioEntity;

/**
 * Mapeador entre {@link EdificioDTO} y {@link EdificioEntity}.
 */
@Mapper(componentModel = "spring", uses = { AmbitoMapper.class, UsuarioMapper.class })
public interface EdificioMapper extends TramaMapper<EdificioDTO, EdificioEntity> {

	@Mappings({ @Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "idHusoHorario", source = "husoHorario.id"),
			@Mapping(target = "listaUsuario", source = "listaUsuario"),
			@Mapping(target = "listaCalendarioFestivo", source = "listaCalendarioFestivo")

	})
	EdificioDTO toDomain(EdificioEntity entity);

	@Mappings({ @Mapping(target = "ambito.id", source = "idAmbito"),
			@Mapping(target = "husoHorario.id", source = "idHusoHorario"),
			@Mapping(target = "listaUsuario", source = "listaUsuario"),
			@Mapping(target = "listaCalendarioFestivo", source = "listaCalendarioFestivo")

	})
	EdificioEntity toEntity(EdificioDTO dto);

	// para que pase del search criteria al filtro
	EdificioFilter toFilter(EdificioSearchCriteria searchCriteria);

	List<EdificioDTO> toEdificioDTOs(List<EdificioEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<EdificioDTO> toEdificioPageResponse(Page<EdificioEntity> page) {
		SearchPageResponse<EdificioDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toEdificioDTOs(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
