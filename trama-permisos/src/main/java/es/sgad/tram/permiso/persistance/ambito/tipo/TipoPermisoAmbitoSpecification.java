package es.sgad.trama.permiso.persistance.ambito.tipo;


import java.util.Date;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.permiso.persistance.entity.tipo.TipoPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.tipoDuracion.TipoDuracionEntity;

public class TipoPermisoAmbitoSpecification {
	// Hay que copiar esta clase cuando se quiera hacer una nueva Specification y
	// cambiar los metodos
	//

//	Hay que añadir en este metodo todos los posibles filtros por los que se quiere poder buscar
	public static Specification<TipoPermisoEntity> filterBy(
			TipoPermisoAmbitoFilter tipoPermisoFilter) {
		return Specification.where(hasIdTipoDuracion(tipoPermisoFilter.idTipoDuracion()))
				.and(hasDescripcion(tipoPermisoFilter.descripcion()))
				.and(hasOrden(tipoPermisoFilter.orden()))
				.and(isComprobarPlazo(tipoPermisoFilter.comprobarPlazo()))
				.and(isEsTiempoComputable(tipoPermisoFilter.esTiempoComputable()))
				.and(isSePuedeSolicitar(tipoPermisoFilter.sePuedeSolicitar()))
				.and(isDebeTenerAdjunto(tipoPermisoFilter.debeTenerAdjunto()))
				.and(isCreadaPorOrganismo(tipoPermisoFilter.creadaPorOrganismo()))
				.and(isCrearEnAmbitoNuevo(tipoPermisoFilter.crearEnAmbitoNuevo()))
				.and(hasFechaBaja(tipoPermisoFilter.fechaBaja()));

	}

//	 Para los casos donde hay que hacer un JOIN con otra tabla
//	Cambiar nombre y entidades que se tratan ademas de la clave por la que se busca
	public static Specification<TipoPermisoEntity> hasIdTipoDuracion(Long idTipoDuracion) {
		return (root, query, criteriaBuilder) -> {
			if (idTipoDuracion == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<TipoPermisoEntity, TipoDuracionEntity> joinTipoDuracion = root
						.join("tipoDuracion");
				return criteriaBuilder.equal(joinTipoDuracion.get("id"), idTipoDuracion);
			}
		};

	}

	// Para los casos que comprueba un campo
//	Cambiar nombre y entidades que se tratan ademas de la clave por la que se busca
	public static Specification<TipoPermisoEntity> hasDescripcion(String descripcion) {
		return (root, query, criteriaBuilder) -> descripcion == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("descripcion"), descripcion);
	}
	public static Specification<TipoPermisoEntity> hasOrden(Integer orden) {
		return (root, query, criteriaBuilder) -> orden == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("orden"), orden);
	}
	public static Specification<TipoPermisoEntity> isComprobarPlazo(Boolean comprobarPlazo) {
		return (root, query, criteriaBuilder) -> comprobarPlazo == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("comprobarPlazo"), comprobarPlazo);
	}
	public static Specification<TipoPermisoEntity> isEsTiempoComputable(Boolean esTiempoComputable) {
		return (root, query, criteriaBuilder) -> esTiempoComputable == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("esTiempoComputable"), esTiempoComputable);
	}
	public static Specification<TipoPermisoEntity> isSePuedeSolicitar(Boolean sePuedeSolicitar) {
		return (root, query, criteriaBuilder) -> sePuedeSolicitar == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("sePuedeSolicitar"), sePuedeSolicitar);
	}
	public static Specification<TipoPermisoEntity> isDebeTenerAdjunto(Boolean debeTenerAdjunto) {
		return (root, query, criteriaBuilder) -> debeTenerAdjunto == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("debeTenerAdjunto"), debeTenerAdjunto);
	}
	public static Specification<TipoPermisoEntity> isCreadaPorOrganismo(Boolean creadaPorOrganismo) {
		return (root, query, criteriaBuilder) -> creadaPorOrganismo == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("creadaPorOrganismo"), creadaPorOrganismo);
	}
	public static Specification<TipoPermisoEntity> isCrearEnAmbitoNuevo(Boolean crearEnAmbitoNuevo) {
		return (root, query, criteriaBuilder) -> crearEnAmbitoNuevo == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("crearEnAmbitoNuevo"), crearEnAmbitoNuevo);
	}
	public static Specification<TipoPermisoEntity> hasFechaBaja(Date fechaBaja) {
		return (root, query, criteriaBuilder) -> fechaBaja == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaBaja"), fechaBaja);
	}
}
