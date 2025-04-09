package es.sgad.trama.permiso.persistance.solicitudPermisoDetalle;

import java.sql.Date;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;


public class SolicitudPermisoDetalleSpecification {

	// Hay que copiar esta clase cuando se quiera hacer una nueva Specification y
	// cambiar los metodos
	//

//	Hay que añadir en este metodo todos los posibles filtros por los que se quiere poder buscar
	public static Specification<SolicitudPermisoDetalleEntity> filterBy(
			SolicitudPermisoDetalleFilter solicitudPermisoDetalleFilter) {
		return Specification.where(hasIdSolicitudPermiso(solicitudPermisoDetalleFilter.idSolicitudPermiso()))
				.and(hasFechaInicio(solicitudPermisoDetalleFilter.fechaInicio()))
				.and(hasFechaFin(solicitudPermisoDetalleFilter.fechaFin()))
				.and(hasHoraInicio(solicitudPermisoDetalleFilter.horaInicio()))
				.and(hasHoraFin(solicitudPermisoDetalleFilter.horaFin()))
				.and(hasObservaciones(solicitudPermisoDetalleFilter.observaciones()));

	}

//	 Para los casos donde hay que hacer un JOIN con otra tabla
//	Cambiar nombre y entidades que se tratan ademas de la clave por la que se busca
//	public static Specification<SolicitudPermisoDetalleEntity> hasIdSolicitudPermiso(Long idSolicitudPermiso) {
//		return (root, query, criteriaBuilder) -> {
//			if (idSolicitudPermiso == null) {
//				return criteriaBuilder.conjunction();
//			} else {
//				Join<SolicitudPermisoDetalleEntity, SolicitudPermisoEntity> solicitanteSolicitudPermiso = root
//						.join("solicitudPermiso");
//				return criteriaBuilder.equal(solicitanteSolicitudPermiso.get("id"), idSolicitudPermiso);
//			}
//		};
//
//	}
	
	public static Specification<SolicitudPermisoDetalleEntity> hasIdSolicitudPermiso(Long idSolicitudPermiso) {
	    return (root, query, criteriaBuilder) -> {
	        if (idSolicitudPermiso == null) {
	            return criteriaBuilder.conjunction();
	        } else {
	            Join<SolicitudPermisoDetalleEntity, SolicitudPermisoEntity> solicitanteSolicitudPermiso =
	                root.join("solicitudPermiso");

	            Predicate predicate = criteriaBuilder.equal(solicitanteSolicitudPermiso.get("id"), idSolicitudPermiso);
	            return predicate;
	        }
	    };
	}

	

	// Para los casos que comprueba un campo
//	Cambiar nombre y entidades que se tratan ademas de la clave por la que se busca
	public static Specification<SolicitudPermisoDetalleEntity> hasFechaInicio(Date fechaInicio) {
		return (root, query, criteriaBuilder) -> fechaInicio == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaInicio"), fechaInicio);
	}

	public static Specification<SolicitudPermisoDetalleEntity> hasFechaFin(Date fechaFin) {
		return (root, query, criteriaBuilder) -> fechaFin == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaFin"), fechaFin);
	}


	public static Specification<SolicitudPermisoDetalleEntity> hasHoraInicio(Date horaInicio) {
		return (root, query, criteriaBuilder) -> horaInicio == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("horaInicio"), horaInicio);
	}

	public static Specification<SolicitudPermisoDetalleEntity> hasHoraFin(Date horaFin) {
		return (root, query, criteriaBuilder) -> horaFin == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("horaFin"), horaFin);
	}

	public static Specification<SolicitudPermisoDetalleEntity> hasObservaciones(String observaciones) {
		return (root, query, criteriaBuilder) -> observaciones == null || observaciones.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("observaciones"), observaciones);
	}

}
