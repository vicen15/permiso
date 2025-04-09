package es.sgad.trama.permiso.service.solicitudPermisoDetalle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.mapper.solicitudPermisoDetalle.SolicitudPermisoDetalleMapper;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;
import es.sgad.trama.permiso.persistance.solicitudPermiso.SolicitudPermisoRepository;
import es.sgad.trama.permiso.persistance.solicitudPermisoDetalle.SolicitudPermisoDetalleFilter;
import es.sgad.trama.permiso.persistance.solicitudPermisoDetalle.SolicitudPermisoDetalleRepository;
import es.sgad.trama.permiso.persistance.solicitudPermisoDetalle.SolicitudPermisoDetalleSpecification;

@Service
public class SolicitudPermisoDetalleServiceImpl implements SolicitudPermisoDetalleService {

	@Autowired
	private SolicitudPermisoDetalleRepository solicitudPermisoDetalleRepository;

	@Autowired
	private SolicitudPermisoRepository solicitudPermisoRepository;

	@Autowired
	private SolicitudPermisoDetalleMapper solicitudPermisoDetalleMapper;

	@Override
	public SolicitudPermisoDetalleDTO getsolicitudPermisoDetallebyId(String id) {

		SolicitudPermisoDetalleEntity solicitudPermisoDetalleEntity = this.solicitudPermisoDetalleRepository
				.findById(id).orElse(null);
		if (solicitudPermisoDetalleEntity != null) {
			if (solicitudPermisoDetalleEntity.getSolicitudPermiso() != null) {
				// This will trigger the lazy loading of the huso horario entity
				solicitudPermisoDetalleEntity.getSolicitudPermiso().getId();
			}
		}
		return solicitudPermisoDetalleMapper.toDomain(solicitudPermisoDetalleEntity);

	}

	@Override
	public SolicitudPermisoDetalleDTO saveSolicitudPermisoDetalle(
			SolicitudPermisoDetalleDTO solicitudInicdenciaDetalleDTO) {
		SolicitudPermisoDetalleEntity solicitudPermisoDetalleEntity = solicitudPermisoDetalleMapper
				.toEntity(solicitudInicdenciaDetalleDTO);
		solicitudPermisoDetalleEntity = solicitudPermisoDetalleRepository.save(solicitudPermisoDetalleEntity);
		return solicitudPermisoDetalleMapper.toDomain(solicitudPermisoDetalleEntity);
	}

	@Override
	public void deleteSolicitudPermisoDetalle(String id) {
		this.solicitudPermisoDetalleRepository.deleteById(id);
	}

	@Override
	public List<SolicitudPermisoDetalleDTO> getAllsolicitudPermisoDetalle() {
		return solicitudPermisoDetalleMapper.toDomain(this.solicitudPermisoDetalleRepository.findAll()).stream()
				.toList();
	}

	@Override
	public SolicitudPermisoDetalleDTO modificaSolicitudPermisoDetalle(String id,
			SolicitudPermisoDetalleDTO solicitudPermisoDetalleDTO) {

		SolicitudPermisoDetalleEntity modifySolicitudPermisoDetalleEntity = solicitudPermisoDetalleRepository
				.findById(id).orElse(null);
		if (modifySolicitudPermisoDetalleEntity != null) {
			if (solicitudPermisoDetalleDTO.getIdSolicitudPermiso() != null) {
				SolicitudPermisoEntity solicitudPermisoEntity = solicitudPermisoRepository
						.findById(solicitudPermisoDetalleDTO.getIdSolicitudPermiso()).orElse(null);
				modifySolicitudPermisoDetalleEntity.setSolicitudPermiso(solicitudPermisoEntity);
			}
			if (solicitudPermisoDetalleDTO.getFechaInicio() != null) {
				modifySolicitudPermisoDetalleEntity.setFechaInicio(solicitudPermisoDetalleDTO.getFechaInicio());
			}
			if (solicitudPermisoDetalleDTO.getFechaFin() != null) {
				modifySolicitudPermisoDetalleEntity.setFechaFin(solicitudPermisoDetalleDTO.getFechaFin());
			}			
			if (solicitudPermisoDetalleDTO.getHoraInicio() != null) {
				modifySolicitudPermisoDetalleEntity.setHoraInicio(solicitudPermisoDetalleDTO.getHoraInicio());
			}
			if (solicitudPermisoDetalleDTO.getHoraFin() != null) {
				modifySolicitudPermisoDetalleEntity.setHoraFin(solicitudPermisoDetalleDTO.getHoraFin());
			}
			modifySolicitudPermisoDetalleEntity = this.solicitudPermisoDetalleRepository
					.save(modifySolicitudPermisoDetalleEntity);
		}

		return solicitudPermisoDetalleMapper.toDomain(modifySolicitudPermisoDetalleEntity);
	}

	@Override
	public SearchPageResponse<SolicitudPermisoDetalleDTO> searchSolicitudPermisoDetalle(
			SolicitudPermisoDetalleFilter filter, int page, int size) {
		Specification<SolicitudPermisoDetalleEntity> spec = SolicitudPermisoDetalleSpecification.filterBy(filter);
        Page<SolicitudPermisoDetalleEntity> pageResult = solicitudPermisoDetalleRepository.findAll(spec, PageRequest.of(page, size));
        return solicitudPermisoDetalleMapper.toSolicitudPermisoDetallePageResponse(pageResult);

	}
}
