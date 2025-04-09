package es.sgad.trama.permiso.service.tipoPermisoAmbito;

import java.util.List;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoDTO;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoFilter;

public interface TipoPermisoAmbitoService{

	TipoPermisoAmbitoDTO getTipoPermisoAmbitoById(String id);

	TipoPermisoAmbitoDTO saveTipoPermisoAmbito(TipoPermisoAmbitoDTO tipoPermisoAmbitoDTO);

	void deleteTipoPermisoAmbito(String id);

	List<TipoPermisoAmbitoDTO> getAllTipoPermisoAmbito();
	
	List<TipoPermisoAmbitoDTO> getTipoPermisoAmbitoByIdAmbito(String idAmbito);
	
	List<TipoPermisoAmbitoDTO> getTipoPermisoAmbitoByIdAmbitoAndIdTipoPermiso(String idAmbito, String idTipoPermiso);
	
	TipoPermisoAmbitoDTO modificarTipoPermisoAmbito(String id, TipoPermisoAmbitoDTO tipoPermisoAmbitoDTO);
	
	SearchPageResponse<TipoPermisoAmbitoDTO> searchTipoPermisoAmbito(TipoPermisoAmbitoFilter filter,
			int page, int size);
}
