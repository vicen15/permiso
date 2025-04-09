package es.sgad.trama.permiso.mapper.solicitudPermisoDocumento;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoDTO;
import es.sgad.trama.permiso.mapper.TramaMapper;
import es.sgad.trama.permiso.mapper.solicitudPermiso.SolicitudPermisoMapper;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDocumento.SolicitudPermisoDocumentoEntity;

@Mapper(componentModel = "spring", uses = {SolicitudPermisoMapper.class})
public interface SolicitudPermisoDocumentoMapper extends TramaMapper<SolicitudPermisoDocumentoDTO, SolicitudPermisoDocumentoEntity>{
	
	@Mappings({
		@Mapping(target = "idSolicitudPermiso", source = "solicitudPermiso.id")
	})
	SolicitudPermisoDocumentoDTO toDomain(SolicitudPermisoDocumentoEntity solicitudPermisoDocumentoEntity);
	@Mappings({
		@Mapping(target = "solicitudPermiso.id", source = "idSolicitudPermiso")
	})
	SolicitudPermisoDocumentoEntity toEntity(SolicitudPermisoDocumentoDTO solicitudPermisoDocumentoDTO);
	
}
