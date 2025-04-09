package es.sgad.trama.permiso.service.solicitudPermisoDocumento;

import java.util.List;

import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoDTO;

public interface SolicitudPermisoDocumentoService {

	
	SolicitudPermisoDocumentoDTO getSolicitudPermisoDocumentoById(String id);
	
	SolicitudPermisoDocumentoDTO saveSolicitudPermisoDocumento(SolicitudPermisoDocumentoDTO solicitudPermisoDocumentoDTO);
	
	void deleteSolicitudPermisoDocumento(String id);
	
	List<SolicitudPermisoDocumentoDTO> getAllSolicitudPermisoDocumento();
	
	SolicitudPermisoDocumentoDTO modificaSolicitudPermisoDocumento(String id, SolicitudPermisoDocumentoDTO solicitudPermisoDocumentoDTO);
	
	//TODO implementar search si fuera necesario
	
}
