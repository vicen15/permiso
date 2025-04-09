package es.sgad.trama.permiso.persistance;


import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.permiso.persistance.entity.PermisoEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.TipoPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;

public class PermisoSpecification {
	// Hay que copiar esta clase cuando se quiera hacer una nueva Specification y
	// cambiar los metodos

	// Hay que añadir en este metodo todos los posibles filtros por los que se quiere poder buscar
	public static Specification<PermisoEntity> filterBy(
			PermisoFilter permisoFilter) {
		return Specification.where(hasIdTipoPermiso(permisoFilter.idTipoPermiso()))
				.and(hasIdUsuarioSolicitante(permisoFilter.idUsuarioSolicitante()))
				.and(hasFecha(permisoFilter.fecha()))
				.and(hasHoraInicio(permisoFilter.horaInicio()))
				.and(hasHoraFin(permisoFilter.horaFin()))
				.and(hasEjercicio(permisoFilter.ejercicio()));

	}

	// Para los casos donde hay que hacer un JOIN con otra tabla
	// Cambiar nombre y entidades que se tratan ademas de la clave por la que se busca
	public static Specification<PermisoEntity> hasIdTipoPermiso(String idTipoPermiso) {
		return (root, query, criteriaBuilder) -> {
			if (idTipoPermiso == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<PermisoEntity, TipoPermisoEntity> joinTipoPermiso = root
						.join("tipoPermiso");
				return criteriaBuilder.equal(joinTipoPermiso.get("id"), idTipoPermiso);
			}
		};

	}
	
	public static Specification<PermisoEntity> hasIdUsuarioSolicitante(String idUsuarioSolicitante) {
		return (root, query, criteriaBuilder) -> {
			if (idUsuarioSolicitante == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<PermisoEntity, UsuarioEntity> joinUsuario = root
						.join("usuario");
				return criteriaBuilder.equal(joinUsuario.get("id"), idUsuarioSolicitante);
			}
		};

	}

	// Para los casos que comprueba un campo
	// Cambiar nombre y entidades que se tratan ademas de la clave por la que se busca
	public static Specification<PermisoEntity> hasFecha(LocalDate fecha) {
		return (root, query, criteriaBuilder) -> fecha == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fecha"), fecha);
	}
	public static Specification<PermisoEntity> hasHoraInicio(LocalTime horaInicio) {
		return (root, query, criteriaBuilder) -> horaInicio == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("horaInicio"), horaInicio);
	}
	public static Specification<PermisoEntity> hasHoraFin(LocalTime horaFin) {
		return (root, query, criteriaBuilder) -> horaFin == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("horaFin"), horaFin);
	}
	public static Specification<PermisoEntity> hasEjercicio(Integer ejercicio) {
		return (root, query, criteriaBuilder) -> ejercicio == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("ejercicio"), ejercicio);
	}
	
}
