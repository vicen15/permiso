package es.sgad.trama.permiso.service.solicitudPermisoDetalle;

import java.util.List;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.persistance.solicitudPermisoDetalle.SolicitudPermisoDetalleFilter;

public interface SolicitudPermisoDetalleService {
	
	SolicitudPermisoDetalleDTO  getsolicitudPermisoDetallebyId(String id);
	
	SolicitudPermisoDetalleDTO saveSolicitudPermisoDetalle(SolicitudPermisoDetalleDTO solicitudInicdenciaDetalleDTO);

	void deleteSolicitudPermisoDetalle(String id);
	
	List<SolicitudPermisoDetalleDTO> getAllsolicitudPermisoDetalle();
	
	SolicitudPermisoDetalleDTO modificaSolicitudPermisoDetalle(String id, SolicitudPermisoDetalleDTO solicitudPermisoDetalleDTO);
	
	SearchPageResponse<SolicitudPermisoDetalleDTO> searchSolicitudPermisoDetalle(SolicitudPermisoDetalleFilter filter,
			int page, int size);
}
