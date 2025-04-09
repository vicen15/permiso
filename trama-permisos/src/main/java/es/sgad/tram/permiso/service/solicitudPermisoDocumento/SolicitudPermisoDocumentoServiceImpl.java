package es.sgad.trama.permiso.service.solicitudPermisoDocumento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoDTO;
import es.sgad.trama.permiso.mapper.solicitudPermisoDocumento.SolicitudPermisoDocumentoMapper;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDocumento.SolicitudPermisoDocumentoEntity;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoRepository;
import es.sgad.trama.permiso.persistance.solicitudPermisoDocumento.SolicitudPermisoDocumentoRepository;

@Service
public class SolicitudPermisoDocumentoServiceImpl implements SolicitudPermisoDocumentoService{
	
	
	@Autowired
	private SolicitudPermisoDocumentoRepository solicitudPermisoDocumentoRepository;
	
	@Autowired
	private SolicitudPermisoRepository solicitudPermisoRepository;
	
	@Autowired
	private SolicitudPermisoDocumentoMapper solicitudPermisoDocumentoMapper;
	
	
	@Override
	public SolicitudPermisoDocumentoDTO getSolicitudPermisoDocumentoById(String id) {
		
		SolicitudPermisoDocumentoEntity solicitudPermisoDocumentoEntity=  this.solicitudPermisoDocumentoRepository.findById(id).orElse(null);
		
		if(solicitudPermisoDocumentoEntity != null) {
			if(solicitudPermisoDocumentoEntity.getSolicitudPermiso() != null) {
				solicitudPermisoDocumentoEntity.getSolicitudPermiso().getId();			
			}
		}
		
		return solicitudPermisoDocumentoMapper.toDomain(solicitudPermisoDocumentoEntity);
	}

	@Override
	public SolicitudPermisoDocumentoDTO saveSolicitudPermisoDocumento(
			SolicitudPermisoDocumentoDTO solicitudPermisoDocumentoDTO) {
		
		SolicitudPermisoDocumentoEntity solicitudPermisoDocumentoEntity = solicitudPermisoDocumentoMapper.toEntity(solicitudPermisoDocumentoDTO);
	
		
		solicitudPermisoDocumentoEntity = solicitudPermisoDocumentoRepository.save(solicitudPermisoDocumentoEntity);		
		return solicitudPermisoDocumentoMapper.toDomain(solicitudPermisoDocumentoEntity);
	}

	@Override
	public void deleteSolicitudPermisoDocumento(String id) {
		this.solicitudPermisoDocumentoRepository.deleteById(id);
	}

	@Override
	public List<SolicitudPermisoDocumentoDTO> getAllSolicitudPermisoDocumento() {

		return this.solicitudPermisoDocumentoMapper.toDomain(this.solicitudPermisoDocumentoRepository.findAll()).stream().toList();
	}

	@Override
	public SolicitudPermisoDocumentoDTO modificaSolicitudPermisoDocumento(String id,
			SolicitudPermisoDocumentoDTO solicitudPermisoDocumentoDTO) {
		
		SolicitudPermisoDocumentoEntity modifySolicitudPermisoDocumentoEntity=  this.solicitudPermisoDocumentoRepository.findById(id).orElse(null);
		//Modificar Permiso asociada
		if(modifySolicitudPermisoDocumentoEntity != null) {
			if(solicitudPermisoDocumentoDTO.getIdSolicitudPermiso() != null) {
				SolicitudPermisoEntity solicitudPermisoentity = solicitudPermisoRepository.findById(solicitudPermisoDocumentoDTO.getIdSolicitudPermiso()).orElse(null);
				//Comprobamos que la solicitud a asignar en el eproceso de modificacion existe
				if(solicitudPermisoentity != null) {
					modifySolicitudPermisoDocumentoEntity.setSolicitudPermiso(solicitudPermisoentity);
				}
			}
		
			if(solicitudPermisoDocumentoDTO.getNombre() != null) {
				modifySolicitudPermisoDocumentoEntity.setNombre(solicitudPermisoDocumentoDTO.getNombre());
			}
			if(solicitudPermisoDocumentoDTO.getDescripcion() != null) {
				modifySolicitudPermisoDocumentoEntity.setDescripcion(solicitudPermisoDocumentoDTO.getDescripcion());
			}
			if(solicitudPermisoDocumentoDTO.getRuta() != null) {
				modifySolicitudPermisoDocumentoEntity.setRuta(solicitudPermisoDocumentoDTO.getRuta());
			}
			if(solicitudPermisoDocumentoDTO.getTipo() != null) {
				modifySolicitudPermisoDocumentoEntity.setTipo(solicitudPermisoDocumentoDTO.getTipo());
			}
			modifySolicitudPermisoDocumentoEntity = solicitudPermisoDocumentoRepository.save(modifySolicitudPermisoDocumentoEntity);
		}
		
		return solicitudPermisoDocumentoMapper.toDomain(modifySolicitudPermisoDocumentoEntity);
	}

}
