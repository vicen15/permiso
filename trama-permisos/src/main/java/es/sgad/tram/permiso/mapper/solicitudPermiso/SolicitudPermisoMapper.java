package es.sgad.trama.permiso.mapper.solicitudPermiso;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.criteria.SolicitudPermisoSearchCriteria;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.firma.FirmaMapper;
import es.sgad.trama.permiso.mapper.solicitudPermisoDetalle.SolicitudPermisoDetalleMapper;
import es.sgad.trama.permiso.mapper.tipoOperacion.TipoOperacionMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoFilter;

/**
 * Mapeador entre {@link SolicitudPermisoEntity} y
 * {@link SolicitudPermisoEntity}.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, TipoOperacionMapper.class, FirmaMapper.class, SolicitudPermisoDetalleMapper.class})
public interface SolicitudPermisoMapper extends TramaMapper<SolicitudPermisoDTO, SolicitudPermisoEntity> {

	@Mappings({
			@Mapping(target = "idTipoPermisoAmbito", source = "tipoPermisoAmbito.id"),
			@Mapping(target = "idSolicitante", source = "solicitante.id"),
			@Mapping(target = "idValidador", source = "validador.id"),
			@Mapping(target = "idAutorizador", source = "autorizador.id"),
			@Mapping(target = "idGestorPersonal", source = "gestorPersonal.id"),
			@Mapping(target = "idGpAsignado", source = "gestorPersonalAsignado.id"),
			@Mapping(target = "motivoDenegacion", source = "motivoDenegacion"),
			@Mapping(target = "fechaSolicitud", source = "fechaSolicitud"),
			@Mapping(target = "fechaValidacion", source = "fechaValidacion"),
			@Mapping(target = "fechaAutorizacion", source = "fechaAutorizacion"),
			@Mapping(target = "fechaGestorPersonal", source = "fechaGestorPersonal"),
			@Mapping(target = "fechaPeticionDocumentacion", source = "fechaPeticionDocumentacion"),
			@Mapping(target = "fechaDesestimacion", source = "fechaDesestimacion"),
			@Mapping(target = "fechaDenegada", source = "fechaDenegada"),
			@Mapping(target = "observacionesValidador", source = "observacionesValidador"),
			@Mapping(target = "observacionesAutorizador", source = "observacionesAutorizador"),
			@Mapping(target = "ejercicio", source = "ejercicio"),
			@Mapping(target = "observacionesParaUsuario", source = "observacionesParaUsuario"),
			@Mapping(target = "idEstadoTramite", source = "estadoTramite.id"),
			@Mapping(target = "observacionesGp", source = "observacionesGp"),
			@Mapping(target = "infoParaGp", source = "infoParaGp"),
			@Mapping(target = "idPermisoAnular", source = "permisoAnular.id"), 
			@Mapping(target = "idAmbito", source = "ambito.id"),
			@Mapping(target = "idTipoOperacion", source="tipoOperacion.id"),
			@Mapping(target = "idFirma", source = "firma.id"),
			@Mapping(target = "listaSolicitudPermisoDetalle", source = "listaSolicitudPermisoDetalle"),
			@Mapping(target = "listaSolicitudPermisoDocumento", source = "listaSolicitudPermisoDocumento")

	})
	SolicitudPermisoDTO toDomain(SolicitudPermisoEntity SolicitudPermisoEntity);

	@InheritInverseConfiguration
	SolicitudPermisoEntity toEntity(SolicitudPermisoDTO SolicitudPermisoEntity);

	SolicitudPermisoFilter toFilter(SolicitudPermisoSearchCriteria searchCriteria);

	List<SolicitudPermisoDTO> toSolicitudPermisoDtos(List<SolicitudPermisoEntity> list);

	default SearchPageResponse<SolicitudPermisoDTO> toSolicitudPermisoPageResponse(
			Page<SolicitudPermisoEntity> page) {
		SearchPageResponse<SolicitudPermisoDTO> pageResponse = new SearchPageResponse<>();
		pageResponse.setContent(toSolicitudPermisoDtos(page.getContent()));
		pageResponse.setPage(page.getNumber());
		pageResponse.setSize(page.getSize());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setTotalSize(page.getTotalElements());
		return pageResponse;
	}

}
