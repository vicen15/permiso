package es.sgad.trama.permiso.api.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import es.sgad.trama.common.log.LogExecutionTime;
import es.sgad.trama.permiso.domain.PermisoDTO;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.criteria.PermisoSearchCriteria;
import es.sgad.trama.permiso.mapper.PermisoMapper;
import es.sgad.trama.permiso.service.PermisoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PermisoController implements PermisoApi {

	private final PermisoService permisoService;

	private final PermisoMapper permisoMapper;
	
	@LogExecutionTime
	@Override
	public ResponseEntity<PermisoDTO> getPermisoById(String id) {
		PermisoDTO permisoDTO = permisoService
				.getPermisoById(id);
		return ResponseEntity.ok(permisoDTO);
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<PermisoDTO> createPermiso(
			PermisoDTO permisoDTO) {
		PermisoDTO savePermisoDTO = permisoService
				.savePermiso(permisoDTO);
		return ResponseEntity.ok(savePermisoDTO);
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<Void> deletePermiso(String id) {
		permisoService.deletePermiso(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<PermisoDTO>> getAllPermiso() {
		return ResponseEntity.ok(permisoService.getAllPermiso());
	}

	@LogExecutionTime
	@Override
	public ResponseEntity<PermisoDTO> modifyPermiso(String id,
			PermisoDTO permisoDTO) {
		PermisoDTO modificaPermisoDTO = permisoService
				.modificarPermiso(id, permisoDTO);
		return ResponseEntity.ok(modificaPermisoDTO);
	}
	
	@LogExecutionTime
	@Override
	public SearchPageResponse<PermisoDTO> searchPermiso(
			PermisoSearchCriteria criteria) {
		return permisoService.searchPermiso(permisoMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
	}


}
