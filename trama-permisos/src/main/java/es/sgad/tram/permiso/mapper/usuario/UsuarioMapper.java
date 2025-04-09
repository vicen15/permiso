package es.sgad.trama.permiso.mapper.usuario;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.domain.usuario.criteria.UsuarioSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.ambito.AmbitoMapper;
import es.sgad.trama.permiso.mapper.edificio.EdificioMapper;
import es.sgad.trama.permiso.mapper.maestras.clasePersonal.ClasePersonalMapper;
import es.sgad.trama.permiso.mapper.rol.rolUsuario.RolUsuarioMapper;
import es.sgad.trama.permiso.mapper.tipoHora.TipoHoraMapper;
import es.sgad.trama.permiso.mapper.usuario.calendarioPersonalDias.CalendarioPersonalDiasMapper;
import es.sgad.trama.permiso.mapper.usuario.extras.UnidadMapper;
import es.sgad.trama.permiso.mapper.usuario.usuarioCalendarioPatron.UsuarioCalendarioPatronMapper;
import es.sgad.trama.permiso.mapper.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoMapper;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.usuario.UsuarioFilter;


/**
 * Mapeador entre {@link UsuarioDTO} y {@link UsuarioEntity}.
 */
/**
 * Mapeador entre {@link UsuarioDTO} y {@link UsuarioEntity}.
 */
@Mapper(componentModel = "spring", uses = { AmbitoMapper.class, TipoHoraMapper.class, EdificioMapper.class, UnidadMapper.class, ClasePersonalMapper.class , RolUsuarioMapper.class, UsuarioCalendarioPatronMapper.class, CalendarioPersonalDiasMapper.class , UsuarioEdificioCalendarioFestivoMapper.class})
public interface UsuarioMapper extends TramaMapper<UsuarioDTO, UsuarioEntity> { 
	
	@Mappings({ @Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "idTipoDocIdent", source = "tipoDocIdent.id"),
			@Mapping(target = "idSexo", source = "sexo.id"), 
			@Mapping(target = "idUnidad", source = "unidad.id"),
			@Mapping(target = "idClasePersonal", source = "clasePersonal.id"),
			@Mapping(target = "idEdificio", source = "edificio.id"), 
			@Mapping(target = "idGrupo", source = "grupo.id"),
			@Mapping(target = "idFlexibilidadTipo", source = "flexibilidadTipo.id"),
			@Mapping(target = "listaCalendarioPersonalDias", source = "listaCalendarioPersonalDias"),
			@Mapping(target = "listaUsuarioCalendarioPatron", source = "listaUsuarioCalendarioPatron"),
			@Mapping(target = "listaUsuarioEdificioCalendarioFestivo", source = "listaUsuarioEdificioCalendarioFestivo"),
			@Mapping(target = "listaRolUsuario", source = "listaRolUsuario"),
			@Mapping(target = "listaValidador", source = "listaValidador"),
			@Mapping(target = "listaAutorizador", source = "listaAutorizador"),
			@Mapping(target = "listaGestorPersonal", source = "listaGestorPersonal"),
			@Mapping(target = "listaGestorPersonalAsignado", source = "listaGestorPersonalAsignado")

	})
	UsuarioDTO toDomain(UsuarioEntity entity);

	@Mappings({ @Mapping(target = "ambito.id", source = "idAmbito"),
			@Mapping(target = "tipoDocIdent.id", source = "idTipoDocIdent"),
			@Mapping(target = "sexo.id", source = "idSexo"), 
			@Mapping(target = "unidad.id", source = "idUnidad"),
			@Mapping(target = "clasePersonal.id", source = "idClasePersonal"),
			@Mapping(target = "edificio.id", source = "idEdificio"), 
			@Mapping(target = "grupo.id", source = "idGrupo"),
			@Mapping(target = "flexibilidadTipo.id", source = "idFlexibilidadTipo"),
			@Mapping(target = "listaCalendarioPersonalDias", source = "listaCalendarioPersonalDias"),
			@Mapping(target = "listaUsuarioCalendarioPatron", source = "listaUsuarioCalendarioPatron"),
			@Mapping(target = "listaUsuarioEdificioCalendarioFestivo", source = "listaUsuarioEdificioCalendarioFestivo"),
			@Mapping(target = "listaRolUsuario", source = "listaRolUsuario"),
			@Mapping(target = "listaValidador", source = "listaValidador"),
			@Mapping(target = "listaAutorizador", source = "listaAutorizador"),
			@Mapping(target = "listaGestorPersonal", source = "listaGestorPersonal"),
			@Mapping(target = "listaGestorPersonalAsignado", source = "listaGestorPersonalAsignado")

	})
	UsuarioEntity toEntity(UsuarioDTO dto);

//	para que pase del search criteria al filtro
	UsuarioFilter toFilter(UsuarioSearchCriteria searchCriteria);

	List<UsuarioDTO> toUsuarioDtos(List<UsuarioEntity> list);

	// mapper para que lo meta en un SearchPageResponse solo hay que cambiar lo que
	// hay entre <> por los que queremos.
	default SearchPageResponse<UsuarioDTO> toUsuarioPageResponse(Page<UsuarioEntity> page) {
		SearchPageResponse<UsuarioDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toUsuarioDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}
	
	default	SolicitanteComboDTO toSolicitanteComboDTO(UsuarioEntity solicitante) {
		
		SolicitanteComboDTO solicitanteComboDTO = new SolicitanteComboDTO();
		
		solicitanteComboDTO.setId(solicitante.getId());		
		solicitanteComboDTO.setNombreCompleto(solicitante.getApellido1() + " " + solicitante.getApellido2() + ", " + solicitante.getNombre());
		
		return solicitanteComboDTO;
	}
	
	
	
	
	
}
