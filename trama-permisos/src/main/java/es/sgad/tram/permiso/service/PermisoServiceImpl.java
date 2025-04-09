package es.sgad.trama.permiso.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import es.sgad.trama.common.exception.InternalErrorException;
import es.sgad.trama.permiso.domain.PermisoDTO;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.mapper.PermisoMapper;
import es.sgad.trama.permiso.persistance.PermisoFilter;
import es.sgad.trama.permiso.persistance.PermisoRepository;
import es.sgad.trama.permiso.persistance.PermisoSpecification;
import es.sgad.trama.permiso.persistance.entity.PermisoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.usuario.UsuarioRepository;

@Service
public class PermisoServiceImpl implements PermisoService {

	@Autowired
	private PermisoRepository permisoRepository;

	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermisoMapper permisoMapper;

	@Override
	public PermisoDTO getPermisoById(String id) {
		PermisoEntity permisoEntity = this.permisoRepository.findById(id).orElse(null);
		if (permisoEntity != null) {
			if (permisoEntity.getIdTipoPermiso() != null) {
				permisoEntity.getIdTipoPermiso();
			}
			
			if (permisoEntity.getUsuario() != null) {
				permisoEntity.getUsuario().getId();
			}
		}
		return permisoMapper.toDomain(permisoEntity);
	}

	@Override
	public PermisoDTO savePermiso(PermisoDTO permisoDTO) {
		PermisoEntity permisoEntity = permisoMapper.toEntity(permisoDTO);
		permisoEntity = permisoRepository.save(permisoEntity);
		return permisoMapper.toDomain(permisoEntity);
	}

	@Override
	public void deletePermiso(String id) {
		permisoRepository.deleteById(id);
	}

	@Override
	public List<PermisoDTO> getAllPermiso() {
		return permisoMapper.toDomain(permisoRepository.findAll()).stream().toList();
	}

	@Override
	public PermisoDTO modificarPermiso(String id, PermisoDTO permisoDTO) {

		PermisoEntity modifyPermisoEntity = this.permisoRepository.findById(id).orElse(null);

		if (modifyPermisoEntity != null) {
			if (permisoDTO.getIdTipoPermiso() != null) {
				modifyPermisoEntity.setIdTipoPermiso(permisoDTO.getIdTipoPermiso());

			}
			if (permisoDTO.getIdSolicitudPermiso() != null) {
				modifyPermisoEntity.setSolicitudPermiso(permisoDTO.getIdSolicitudPermiso());

			}
			if (permisoDTO.getIdUsuarioSolicitante() != null) {
				UsuarioEntity usuarioEntity = usuarioRepository
						.findById(permisoDTO.getIdUsuarioSolicitante().toString()).orElse(null);
				modifyPermisoEntity.setUsuario(usuarioEntity);

			}
			if (permisoDTO.getFechaInicio() != null) {
				modifyPermisoEntity.setFechaInicio(permisoDTO.getFechaInicio());
			}
			if (permisoDTO.getFechaFin() != null) {
				modifyPermisoEntity.setFechaFin(permisoDTO.getFechaFin());
			}
			if (permisoDTO.getHoraInicio() != null) {
				modifyPermisoEntity.setHoraInicio(permisoDTO.getHoraInicio());
			}
			if (permisoDTO.getHoraFin() != null) {
				modifyPermisoEntity.setHoraFin(permisoDTO.getHoraFin());
			}
			if (permisoDTO.getEjercicio() != null) {
				modifyPermisoEntity.setEjercicio(permisoDTO.getEjercicio());
			}
			if (permisoDTO.getNumeroDias() != null) {
				modifyPermisoEntity.setNumeroDias(permisoDTO.getNumeroDias());
			}
			modifyPermisoEntity = permisoRepository.save(modifyPermisoEntity);
		}

		return permisoMapper.toDomain(modifyPermisoEntity);
	}

	@Override
	public SearchPageResponse<PermisoDTO> searchPermiso(PermisoFilter filter, int page, int size) {
		Specification<PermisoEntity> spec = PermisoSpecification.filterBy(filter);
		Page<PermisoEntity> pageResult = permisoRepository.findAll(spec, PageRequest.of(page, size));
		return permisoMapper.toPermisosPageResponse(pageResult);
	}

	@Override
	public Boolean existenPermisosEntreFechasPorUsuario(Boolean nuevoPermisoEsDiario, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String idUsuario) {
		if(fechaInicio == null ) {
            throw new InternalErrorException(
                    new IllegalArgumentException("Parametro fechaInicio no puede ser nulo en la funcion."));
		}

		if(idUsuario == null || StringUtils.isBlank(idUsuario)) {
			 throw new InternalErrorException(
	                    new IllegalArgumentException("Parametro idUsuario no puede ser nulo o blanco."));
		}
		return permisoRepository.contarPermisosEntreFechasPorUsuario(nuevoPermisoEsDiario ? 1 : 0, fechaInicio, fechaFin, horaInicio, horaFin, idUsuario) > 0 ? true : false;
	}
	
}
