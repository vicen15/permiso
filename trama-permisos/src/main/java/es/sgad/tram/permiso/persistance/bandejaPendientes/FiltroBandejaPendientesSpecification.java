package es.sgad.trama.permiso.persistance.bandejaPendientes;

import java.time.LocalDate;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.common.exception.ItemNotFoundException;
import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.estadoTramite.EstadoTramiteEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;
import es.sgad.trama.permiso.persistance.entity.superior.SuperiorEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.TipoPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.tipoOperacion.TipoOperacionEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;


public class FiltroBandejaPendientesSpecification {

	// Hay que copiar esta clase cuando se quiera hacer una nueva Specification y
	// cambiar los metodos
	//

//	Hay que añadir en este metodo todos los posibles filtros por los que se quiere poder buscar
	public static Specification<SolicitudPermisoEntity> filterBy(
			FiltroBandejaPendientesFilter bandejaPendientesFilter) {
		return Specification.where(hasIdTipoPermisoAmbito(bandejaPendientesFilter.idTipoPermisoAmbito()))
				.and(hasIdSolicitante(bandejaPendientesFilter.idSolicitante()))
				.and(hasIdAccion(bandejaPendientesFilter.idAccion(), bandejaPendientesFilter.idUsuario())
				.and(hasIdUsuario(bandejaPendientesFilter.idUsuario()))
				.and(hasIdAmbito(bandejaPendientesFilter.idAmbito()))
				.and(betweenFechaIniFin(bandejaPendientesFilter.fechaInicio(), bandejaPendientesFilter.fechaFin())));
				
	}

	public static Specification<SolicitudPermisoEntity> hasIdTipoPermisoAmbito(String idTipoPermisoAmbito) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idTipoPermisoAmbito)) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, TipoPermisoAmbitoEntity> tipoPermisoAmbito = root.join("tipoPermisoAmbito");
				//return criteriaBuilder.equal(tipoPermiso.get("id"), idTipoPermiso);
				return criteriaBuilder.and(criteriaBuilder.equal(tipoPermisoAmbito.get("id"), idTipoPermisoAmbito), criteriaBuilder.isNull(tipoPermisoAmbito.get("fechaBaja")));
			}
		};

	}
	
	
	public static Specification<SolicitudPermisoEntity> betweenFechaIniFin(LocalDate fechaInicio, LocalDate fechaFin) {
	    return (root, query, criteriaBuilder) -> {
	        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
	            return criteriaBuilder.conjunction();
	        }

	        Join<SolicitudPermisoEntity, SolicitudPermisoDetalleEntity> detalle = root.join("listaSolicitudPermisoDetalle");

	        Predicate solapaInicio = criteriaBuilder.between(detalle.get("fechaInicio"), fechaInicio, fechaFin);
	        
	        Predicate solapaFin = criteriaBuilder.and(
	            criteriaBuilder.isNotNull(detalle.get("fechaFin")),
	            criteriaBuilder.between(detalle.get("fechaFin"), fechaInicio, fechaFin)
	        );

			Predicate wrappedsolapaInicio = criteriaBuilder.and(
                    criteriaBuilder.or(solapaInicio)
            );
            Predicate wrappedSolapaFin = criteriaBuilder.and(
                    criteriaBuilder.or(solapaFin)
            );
            
            return criteriaBuilder.or(
            		wrappedsolapaInicio,
            		wrappedSolapaFin
            );				
	    };
	}

	public static Specification<SolicitudPermisoEntity> hasIdTipoPermiso(String idTipoPermiso) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idTipoPermiso)) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, TipoPermisoEntity> tipoPermiso = root.join("tipoPermisoAmbito");
				return criteriaBuilder.equal(tipoPermiso.get("id"), idTipoPermiso);
			}
		};

	}
	
	public static Specification<SolicitudPermisoEntity> hasIdTipoOperacion(String idTipoOperacion) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idTipoOperacion)) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, TipoOperacionEntity> tipoOperacionPermiso = root.join("tipoOperacion");
				return criteriaBuilder.equal(tipoOperacionPermiso.get("id"), idTipoOperacion);
			}
		};

	}
	

	
	public static Specification<SolicitudPermisoEntity> hasIdAmbito(String idAmbito) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idAmbito)) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, AmbitoEntity> ambitoPermiso = root.join("ambito");
				return criteriaBuilder.equal(ambitoPermiso.get("id"), idAmbito);
			}
		};

	}

	public static Specification<SolicitudPermisoEntity> hasIdSolicitante(String idSolicitante) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idSolicitante)) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitantePermiso = root.join("solicitante");
				return criteriaBuilder.and(criteriaBuilder.equal(solicitantePermiso.get("id"), idSolicitante), 
						criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")));
			}
		};

	}


	public static Specification<SolicitudPermisoEntity> hasIdAccion(String idAccion, String idUsuario) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idAccion)) {
				return criteriaBuilder.conjunction();
			} else {
				
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitantePermiso = root.join("solicitante");

		        Join<SolicitudPermisoEntity, EstadoTramiteEntity> estadoPermiso = root.join("estadoTramite");

		        Join<UsuarioEntity, SuperiorEntity> superior = solicitantePermiso.join("listaSuperiorUsuario", JoinType.LEFT);

		        Join<SuperiorEntity, UsuarioEntity> validador = superior.join("validador", JoinType.LEFT);
		        Join<SuperiorEntity, UsuarioEntity> autorizador = superior.join("autorizador", JoinType.LEFT);

				switch (idAccion) {
				
					// VALIDAR
					case Constantes.VALOR_STRING_VALIDAR:
						return criteriaBuilder.and(
					            criteriaBuilder.equal(validador.get("id"), idUsuario),
					            criteriaBuilder.isNull(validador.get("fechaBaja")),
					            criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_VALIDAR),
					            criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"))
					        );

					// AUTORIZAR
					case Constantes.VALOR_STRING_AUTORIZAR:
						return criteriaBuilder.and(
					            criteriaBuilder.equal(autorizador.get("id"), idUsuario),
					            criteriaBuilder.isNull(autorizador.get("fechaBaja")),
					            criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_AUTORIZAR),
					            criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"))
					        );
								
					// PENDIENTES DE MI
					case Constantes.VALOR_STRING_PTES_DE_MI:
						return criteriaBuilder.and(criteriaBuilder.equal(solicitantePermiso.get("id"), idUsuario),
													criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")),
													criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTES_DE_MI),
													criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
								
					// TODAS
					case Constantes.VALOR_STRING_TODAS:
				        Predicate solicitantePredicate = criteriaBuilder.and(criteriaBuilder.equal(solicitantePermiso.get("id"), idUsuario),
								criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")),
								criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTES_DE_MI),
								criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
					        
				        Predicate validadorPredicate = criteriaBuilder.and(criteriaBuilder.equal(validador.get("id"), idUsuario),
								criteriaBuilder.isNull(validador.get("fechaBaja")),
								criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_VALIDAR),
								criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
	
				        Predicate autorizadorPredicate = criteriaBuilder.and(criteriaBuilder.equal(autorizador.get("id"), idUsuario),
								criteriaBuilder.isNull(autorizador.get("fechaBaja")),
								criteriaBuilder.equal(estadoPermiso.get("id"),Constantes.VALOR_LONG_AUTORIZAR),
								criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
	
				        Predicate wrappedSolicitantePredicate = criteriaBuilder.and(
			                    criteriaBuilder.or(solicitantePredicate));
				        
				        Predicate wrappedValidadorPredicate = criteriaBuilder.and(
			                    criteriaBuilder.or(validadorPredicate));
				        
				        Predicate wrappedAutorizadorPredicate = criteriaBuilder.and(
			                    criteriaBuilder.or(autorizadorPredicate));
				        
				        return criteriaBuilder.or(wrappedSolicitantePredicate,
				        							wrappedValidadorPredicate, 
				        							wrappedAutorizadorPredicate);
			    
					// EN OTRO CASO
					default:
						return criteriaBuilder.conjunction();
							
				}
			}

		};
	}
	
	public static Specification<SolicitudPermisoEntity> hasIdUsuario(String idUsuario) {
	    return (root, query, criteriaBuilder) -> {
	        if (StringUtils.isBlank(idUsuario)) {
	            throw new ItemNotFoundException("Usuario", " no existente. Contacte con el responsable de la aplicación");
	        }

	        Join<SolicitudPermisoEntity, UsuarioEntity> solicitantePermiso = root.join("solicitante");

	        Join<SolicitudPermisoEntity, EstadoTramiteEntity> estadoPermiso = root.join("estadoTramite");

	        Join<UsuarioEntity, SuperiorEntity> superior = solicitantePermiso.join("listaSuperiorUsuario", JoinType.LEFT);

	        Join<SuperiorEntity, UsuarioEntity> validador = superior.join("validador", JoinType.LEFT);
	        Join<SuperiorEntity, UsuarioEntity> autorizador = superior.join("autorizador", JoinType.LEFT);

	        Predicate solicitantePredicate = criteriaBuilder.and(
		            criteriaBuilder.equal(solicitantePermiso.get("id"), idUsuario),
		            criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")),
		            criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_ADJUNTAR_DOC),
		            criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"))
		        );
	        
	        Predicate validadorPredicate = criteriaBuilder.and(
	            criteriaBuilder.equal(validador.get("id"), idUsuario),
	            criteriaBuilder.isNull(validador.get("fechaBaja")),
	            criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_VALIDAR),
	            criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"))
	        );

	        Predicate autorizadorPredicate = criteriaBuilder.and(
	            criteriaBuilder.equal(autorizador.get("id"), idUsuario),
	            criteriaBuilder.isNull(autorizador.get("fechaBaja")),
	            criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_AUTORIZAR),
	            criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"))
	        );

	        Predicate wrappedSolicitantePredicate = criteriaBuilder.and(
                    criteriaBuilder.or(solicitantePredicate));
	        
	        Predicate wrappedValidadorPredicate = criteriaBuilder.and(
                    criteriaBuilder.or(validadorPredicate));
	        
	        Predicate wrappedAutorizadorPredicate = criteriaBuilder.and(
                    criteriaBuilder.or(autorizadorPredicate));
            
	        
	        return criteriaBuilder.or(wrappedSolicitantePredicate,
	        							wrappedValidadorPredicate, 
	        							wrappedAutorizadorPredicate);
	    };
	}
}
