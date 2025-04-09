package es.sgad.trama.permiso.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import es.sgad.trama.permiso.domain.PermisoDTO;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.persistance.PermisoFilter;

public interface PermisoService{

	PermisoDTO getPermisoById(String id);

	PermisoDTO savePermiso(PermisoDTO permisoDTO);

	void deletePermiso(String id);

	List<PermisoDTO> getAllPermiso();

	PermisoDTO modificarPermiso(String id, PermisoDTO permisoDTO);
	
	SearchPageResponse<PermisoDTO> searchPermiso(PermisoFilter filter,
			int page, int size);
	
	Boolean existenPermisosEntreFechasPorUsuario(Boolean nuevo_permiso_es_diario, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String idUsuario);
}
