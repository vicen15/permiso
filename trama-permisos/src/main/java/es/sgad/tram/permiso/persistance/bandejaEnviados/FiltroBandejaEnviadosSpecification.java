package es.sgad.trama.permiso.persistance.bandejaEnviados;

import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.common.exception.ItemNotFoundException;
import es.sgad.trama.common.exception.ValidationException;
import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.estadoTramite.EstadoTramiteEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;

public class FiltroBandejaEnviadosSpecification {

	// Hay que copiar esta clase cuando se quiera hacer una nueva Specification y
	// cambiar los metodos
	//

//	Hay que añadir en este metodo todos los posibles filtros por los que se quiere poder buscar
	public static Specification<SolicitudPermisoEntity> filterBy(
			FiltroBandejaEnviadosFilter bandejaEnviadosFilter) {
		return Specification.where(hasIdUsuario(bandejaEnviadosFilter.idUsuario(), bandejaEnviadosFilter.idAccion())
				.and(hasIdAmbito(bandejaEnviadosFilter.idAmbito()))
				.and(hasIdSolicitante(bandejaEnviadosFilter.idSolicitante()))
				.and(hasIdAccion(bandejaEnviadosFilter.idAccion(), bandejaEnviadosFilter.idUsuario()))
				.and(hasIdEstado(bandejaEnviadosFilter.idEstado()))
				.and(hasIdTipoPermisoAmbito(bandejaEnviadosFilter.idTipoPermisoAmbito())))
				.and(betweenFechaIniFin(bandejaEnviadosFilter.fechaInicio(), bandejaEnviadosFilter.fechaFin()));				
	}

	public static Specification<SolicitudPermisoEntity> betweenFechaIniFin(LocalDate fechaInicio, LocalDate fechaFin) {
	    return (root, query, criteriaBuilder) -> {
	        if (fechaInicio == null && fechaFin == null) {
	        	return criteriaBuilder.conjunction();
	        } else if (fechaInicio == null && fechaFin != null) {
	        	throw new ItemNotFoundException("Fecha Desde", "No se ha introducido la Fecha Desde cuando se ha introducido la Fecha Hasta, las dos son necesarias.");
	        } else if (fechaInicio != null && fechaFin == null) {
	        	throw new ItemNotFoundException("Fecha Hasta", "No se ha introducido la Fecha Hasta cuando se ha introducido la Fecha Desde, las dos son necesarias.");
	        } else if (fechaInicio.isAfter(fechaFin)) {
	        	String message = "Fechas mal introducidas.".concat(" La Fecha Desde ha de ser menor o igual a la Fecha Hasta.");
	        	throw new ValidationException(new HashMap<String, String>(), "Fecha Desde, Fecha Hasta", message, null, message);
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

	public static Specification<SolicitudPermisoEntity> hasIdAmbito(String idAmbito) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idAmbito)) {
				throw new ItemNotFoundException("Ámbito", "El Usuario no tiene asignado un Ámbito activo. Contacte con el responsable de la aplicación");
			} else {
				Join<SolicitudPermisoEntity, AmbitoEntity> ambitoPermiso = root.join("ambito");
				// return criteriaBuilder.equal(ambitoPermiso.get("id"), idAmbito);
				return criteriaBuilder.and(criteriaBuilder.equal(ambitoPermiso.get("id"), idAmbito),
											criteriaBuilder.isNull(ambitoPermiso.get("fechaBaja")));
			}
		};

	}

	public static Specification<SolicitudPermisoEntity> hasIdSolicitante(String idSolicitante) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idSolicitante)) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitantePermiso = root.join("solicitante");
				//return criteriaBuilder.equal(solicitantePermiso.get("id"), idSolicitante);
				return criteriaBuilder.and(criteriaBuilder.equal(solicitantePermiso.get("id"), idSolicitante), 
											criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")));
			}
		};

	}

	public static Specification<SolicitudPermisoEntity> hasIdAccion(String idAccion, String idUsuario) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idAccion) || StringUtils.isBlank(idUsuario)) {
				return criteriaBuilder.conjunction();
			} else {
				
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitantePermiso = root.join("solicitante");
				Join<SolicitudPermisoEntity, UsuarioEntity> validadorPermiso = root.join("validador", JoinType.LEFT);
				Join<SolicitudPermisoEntity, UsuarioEntity> autorizadorPermiso = root.join("autorizador", JoinType.LEFT);
				Join<SolicitudPermisoEntity, EstadoTramiteEntity> estadoPermiso = root.join("estadoTramite");
				
				switch (idAccion) {
				
					// CREADAS POR MI => que sea yo solicitante
					case Constantes.VALOR_STRING_CREADAS_POR_MI:
						return criteriaBuilder.and(criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")),
													criteriaBuilder.equal(solicitantePermiso.get("id"), idUsuario),
													criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")));
					
						// VALIDADAS POR MI => que sea yo validador
					case Constantes.VALOR_STRING_VALIDADAS_POR_MI:
						return criteriaBuilder.and(criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")),
													criteriaBuilder.equal(validadorPermiso.get("id"), idUsuario),
													criteriaBuilder.isNull(validadorPermiso.get("fechaBaja")));
						
					// AUTORIZADAS POR MI => que sea yo autorizador
					case Constantes.VALOR_STRING_AUTORIZADAS_POR_MI:
						return criteriaBuilder.and(criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")),
													criteriaBuilder.equal(autorizadorPermiso.get("id"), idUsuario),
													criteriaBuilder.isNull(autorizadorPermiso.get("fechaBaja")));
						
					// EN OTRO CASO
					default:
						return criteriaBuilder.and(criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")),
								criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_STRING_ESTADO_NO_EXISTENTE),
								criteriaBuilder.equal(autorizadorPermiso.get("id"), idUsuario),
								criteriaBuilder.isNull(autorizadorPermiso.get("fechaBaja")));
				}
			}

		};
	}
	
	public static Specification<SolicitudPermisoEntity> hasIdUsuario(String idUsuario, String idAccion) {
		
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idUsuario) || StringUtils.isBlank(idAccion)) {
				throw new ItemNotFoundException("Usuario", " no existente. Contacte con el responsable de la aplicación");
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitantePermiso = root.join("solicitante");
				Join<SolicitudPermisoEntity, UsuarioEntity> validadorPermiso = root.join("validador", JoinType.LEFT);
				Join<SolicitudPermisoEntity, UsuarioEntity> autorizadorPermiso = root.join("autorizador", JoinType.LEFT);
				
				Predicate solicitantePredicate = null;
				
				if(idAccion.equals(Constantes.VALOR_STRING_CREADAS_POR_MI)) {
					solicitantePredicate = criteriaBuilder.and(
						criteriaBuilder.equal(solicitantePermiso.get("id"), idUsuario),
						criteriaBuilder.isNull(solicitantePermiso.get("fechaBaja")));
				}
				
				Predicate validadorPredicate = null;
				
				if(idAccion.equals(Constantes.VALOR_STRING_VALIDADAS_POR_MI)) {
					validadorPredicate = criteriaBuilder.and(criteriaBuilder.equal(validadorPermiso.get("id"), idUsuario),
																criteriaBuilder.isNull(validadorPermiso.get("fechaBaja")),
																criteriaBuilder.or(criteriaBuilder.isNotNull(root.get("fechaValidacion")),
																					criteriaBuilder.isNotNull(root.get("fechaDenegada"))));
				}
				
 
				Predicate autorizadorPredicate = null;
						
				if(idAccion.equals(Constantes.VALOR_STRING_AUTORIZADAS_POR_MI)) {		
					autorizadorPredicate = criteriaBuilder.and(criteriaBuilder.equal(autorizadorPermiso.get("id"), idUsuario),
																criteriaBuilder.isNull(autorizadorPermiso.get("fechaBaja")),
																criteriaBuilder.or(criteriaBuilder.isNotNull(root.get("fechaAutorizacion")),
																					criteriaBuilder.isNotNull(root.get("fechaDenegada"))));
				}
				
				
				Predicate wrappedSolicitantePredicate = null;
						
				if(solicitantePredicate != null) {
					wrappedSolicitantePredicate = criteriaBuilder.and(
	                    criteriaBuilder.or(solicitantePredicate));
				}else{
					wrappedSolicitantePredicate = criteriaBuilder.conjunction();
				}
				
				Predicate wrappedValidadorPredicate = null;
						
				if(validadorPredicate != null) {
					wrappedValidadorPredicate = criteriaBuilder.and(
	                    criteriaBuilder.or(validadorPredicate));
				}else{
					wrappedValidadorPredicate = criteriaBuilder.conjunction();
				}
	            
				Predicate wrappedAutorizadorPredicate = null;
						
	            if(autorizadorPredicate != null) {
	            	wrappedAutorizadorPredicate = criteriaBuilder.and(
	                    criteriaBuilder.or(autorizadorPredicate));
	            }else{
	            	wrappedAutorizadorPredicate = criteriaBuilder.conjunction();
	            }
	            
	            return criteriaBuilder.or(
	                    wrappedSolicitantePredicate,
	                    wrappedValidadorPredicate,
	                    wrappedAutorizadorPredicate
	            );				
			}		
		};
 
	}
	
	public static Specification<SolicitudPermisoEntity> hasIdEstado(String idEstado) {
		return (root, query, criteriaBuilder) -> {
			if (StringUtils.isBlank(idEstado)) {
				return criteriaBuilder.conjunction();
			} else {

				Join<SolicitudPermisoEntity, EstadoTramiteEntity> estadoPermiso = root.join("estadoTramite");

				switch (idEstado) {

					// TODOS
					case Constantes.VALOR_STRING_TODAS:
						return criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"));
						
					// EN PROCESO
					case Constantes.VALOR_STRING_EN_PROCESO:
						
						Predicate estadoPredicate = criteriaBuilder.or(
								criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_ADJUNTAR_DOC),
								criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_VALIDAR),
								criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_AUTORIZAR));
						
						Predicate fBajaPredicate = criteriaBuilder.isNull(estadoPermiso.get("fechaBaja"));
						
						Predicate wrappedEstadoPredicate = criteriaBuilder.and(
			                    criteriaBuilder.or(estadoPredicate)
			            );
			            Predicate wrappedFBajaPredicate = criteriaBuilder.and(
			                    criteriaBuilder.or(fBajaPredicate)
			            );
			           
			            return criteriaBuilder.and(
			            		wrappedEstadoPredicate,
			            		wrappedFBajaPredicate
			            );				
						
/*						return criteriaBuilder.and(criteriaBuilder.or(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_VALIDAR),
																	  criteriaBuilder.or(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_AUTORIZAR),
																						 criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_PTE_ADJUNTAR_DOC))),
												   criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));*/
						
					// ADMITIDAS
					case Constantes.VALOR_STRING_ADMITIDAS:
						return criteriaBuilder.and(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_ADMITIDAS),
													criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
						
					// DENEGADAS
					case Constantes.VALOR_STRING_DENEGADAS:
						return criteriaBuilder.and(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_DENEGADAS),
													criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
						
					// CANCELADAS
					case Constantes.VALOR_STRING_CANCELADAS:
						return criteriaBuilder.and(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_CANCELADAS),
													criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
						
					// DESISTIDAS
					case Constantes.VALOR_STRING_DESISTIDAS:
						return criteriaBuilder.and(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_LONG_DESISTIDAS),
													criteriaBuilder.isNull(estadoPermiso.get("fechaBaja")));
						
					// EN OTRO CASO
					default:
						return criteriaBuilder.and(criteriaBuilder.equal(estadoPermiso.get("id"), Constantes.VALOR_STRING_ESTADO_NO_EXISTENTE));
			
				}
			}
		};

	}

}
