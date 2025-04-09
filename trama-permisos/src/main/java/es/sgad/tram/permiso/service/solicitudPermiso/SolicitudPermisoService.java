package es.sgad.trama.permiso.service.solicitudPermiso;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoFilter;

public interface SolicitudPermisoService {

	SolicitudPermisoDTO saveNuevaSolicitudPermiso(NuevaSolicitudPermisoDTO solicitudPermisoDTO, List<MultipartFile> archivos, BindingResult bindingResult);
	
	SearchPageResponse<SolicitudPermisoDTO> searchSolicitudPermiso(SolicitudPermisoFilter filter,
			int page, int size);
	
	SolicitudPermisoDTO getSolicitudPermisoById(String id);
}
