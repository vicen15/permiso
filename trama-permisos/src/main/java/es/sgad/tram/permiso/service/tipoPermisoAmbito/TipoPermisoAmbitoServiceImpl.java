package es.sgad.trama.permiso.service.tipoPermisoAmbito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoDTO;
import es.sgad.trama.permiso.mapper.tipoPermisoAmbito.TipoPermisoAmbitoMapper;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoFilter;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoRepository;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;

@Service
public class TipoPermisoAmbitoServiceImpl implements TipoPermisoAmbitoService{
	
	
	@Autowired
	TipoPermisoAmbitoRepository tipoPermisoAmbitoRepository;
	
	@Autowired
	TipoPermisoAmbitoMapper tipoPermisoAmbitoMapper;
	
	@Override
	public TipoPermisoAmbitoDTO getTipoPermisoAmbitoById(String id) {
		TipoPermisoAmbitoEntity tipoPermisoAmbitoEntity = this.tipoPermisoAmbitoRepository.findById(id).orElse(null);
		return tipoPermisoAmbitoMapper.toDomain(tipoPermisoAmbitoEntity);
	}

	@Override
	public TipoPermisoAmbitoDTO saveTipoPermisoAmbito(TipoPermisoAmbitoDTO tipoPermisoAmbitoDTO) {
		
		TipoPermisoAmbitoEntity tipoPermisoAmbitoEntity = tipoPermisoAmbitoMapper.toEntity(tipoPermisoAmbitoDTO);
		
		tipoPermisoAmbitoEntity = tipoPermisoAmbitoRepository.save(tipoPermisoAmbitoEntity);
		
		return tipoPermisoAmbitoMapper.toDomain(tipoPermisoAmbitoEntity);
	}

	@Override
	public void deleteTipoPermisoAmbito(String id) {
		tipoPermisoAmbitoRepository.deleteById(id);
	}

	@Override
	public List<TipoPermisoAmbitoDTO> getAllTipoPermisoAmbito() {
		
		return tipoPermisoAmbitoMapper.toDomain(tipoPermisoAmbitoRepository.findAll()).stream().toList();
	}

	@Override
	public List<TipoPermisoAmbitoDTO> getTipoPermisoAmbitoByIdAmbito(String idAmbito) {
		
		return tipoPermisoAmbitoMapper.toDomain(tipoPermisoAmbitoRepository.findTipoPermisoAmbitoByIdAmbito(idAmbito)).stream().toList();
	}

	@Override
	public List<TipoPermisoAmbitoDTO> getTipoPermisoAmbitoByIdAmbitoAndIdTipoPermiso(String idAmbito,
			String idTipoPermiso) {
		return tipoPermisoAmbitoMapper.toDomain(tipoPermisoAmbitoRepository.findTipoPermisoAmbitoByIdAmbitoAndIdTipoPermiso(idAmbito,idTipoPermiso)).stream().toList();
	}

	@Override
	public TipoPermisoAmbitoDTO modificarTipoPermisoAmbito(String id,
			TipoPermisoAmbitoDTO tipoPermisoAmbitoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchPageResponse<TipoPermisoAmbitoDTO> searchTipoPermisoAmbito(TipoPermisoAmbitoFilter filter,
			int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
