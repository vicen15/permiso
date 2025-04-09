package es.sgad.trama.permiso.persistance.solicitudPermiso;

import java.sql.Date;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.permiso.persistance.entity.PermisoEntity;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.estadoTramite.EstadoTramiteEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoFirma.SolicitudPermisoFirmaEntity;
import es.sgad.trama.permiso.persistance.entity.tipoOperacion.TipoOperacionEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;

public class SolicitudPermisoSpecification {

	public static Specification<SolicitudPermisoEntity> filterBy(
			SolicitudPermisoFilter solicitudPermisoFilter) {
		return Specification.where(hasIdTipoPermiso(solicitudPermisoFilter.idTipoPermiso()))
				.and(hasIdSolicitante(solicitudPermisoFilter.idSolicitante()))
				.and(hasIdValidador(solicitudPermisoFilter.idValidador()))
				.and(hasIdAutorizador(solicitudPermisoFilter.idAutorizador()))
				.and(hasIdGestorPersonal(solicitudPermisoFilter.idGestorPersonal()))
				.and(hasIdGPAsignado(solicitudPermisoFilter.idGPAsignado()))
				.and(hasIdEstadoTramite(solicitudPermisoFilter.idEstadoTramite()))
				.and(hasMotivoDenegacion(solicitudPermisoFilter.motivoDenegacion()))
				.and(hasFechaSolicitud(solicitudPermisoFilter.fechaSolicitud()))
				.and(hasFechaValidacion(solicitudPermisoFilter.fechaValidacion()))
				.and(hasFechaAutorizacion(solicitudPermisoFilter.fechaAutorizacion()))
				.and(hasFechaGestorPersonal(solicitudPermisoFilter.fechaGestorPersonal()))
				.and(hasFechaPeticionDocumentacion(solicitudPermisoFilter.fechaPeticionDocumentacion()))
				.and(hasFechaDesestimacion(solicitudPermisoFilter.fechaDesestimacion()))
				.and(hasFechaDenegacion(solicitudPermisoFilter.fechaDenegacion()))
				.and(hasIdFirma(solicitudPermisoFilter.idFirma()))
				.and(hasObservacionesValidador(solicitudPermisoFilter.observacionesValidador()))
				.and(hasObservacionesAutorizador(solicitudPermisoFilter.observacionesAutorizador()))
				.and(hasEjercicio(solicitudPermisoFilter.ejercicio()))
				.and(hasObservacionesParaUsuario(solicitudPermisoFilter.observacionesParaUsuario()))
				.and(hasObservacionesGp(solicitudPermisoFilter.observacionesGp()))
				.and(hasInfoParaGp(solicitudPermisoFilter.infoParaGp()))
				.and(hasIdTipoOperacion(solicitudPermisoFilter.idTipoOperacion()))
				.and(hasIdSolicitudAnular(solicitudPermisoFilter.idSolicitudAnular()))
				.and(hasIdAmbito(solicitudPermisoFilter.idAmbito()));

	}

	public static Specification<SolicitudPermisoEntity> hasIdTipoPermiso(Long idTipoPermiso) {
		return (root, query, criteriaBuilder) -> {
			if (idTipoPermiso == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitanteSolicitudPermiso = root
						.join("idTipoPermiso");
				return criteriaBuilder.equal(solicitanteSolicitudPermiso.get("id"), idTipoPermiso);

			}

		};
	}
	
	
	public static Specification<SolicitudPermisoEntity> hasIdSolicitante(Long idSolicitante) {
		return (root, query, criteriaBuilder) -> {
			if (idSolicitante == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> solicitanteSolicitudPermiso = root
						.join("solicitante");
				return criteriaBuilder.equal(solicitanteSolicitudPermiso.get("id"), idSolicitante);

			}

		};
	}

	public static Specification<SolicitudPermisoEntity> hasIdValidador(Long idValidador) {
		return (root, query, criteriaBuilder) -> {
			if (idValidador == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> validadorSolicitudPermiso = root.join("validador");
				return criteriaBuilder.equal(validadorSolicitudPermiso.get("id"), idValidador);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> hasIdAutorizador(Long idAutorizador) {
		return (root, query, criteriaBuilder) -> {
			if (idAutorizador == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> autorizadorSolicitudPermiso = root
						.join("autorizador");
				return criteriaBuilder.equal(autorizadorSolicitudPermiso.get("id"), idAutorizador);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> hasIdGestorPersonal(Long idGestorPersonal) {
		return (root, query, criteriaBuilder) -> {
			if (idGestorPersonal == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> gestorPersonalSolicitudPermiso = root
						.join("gestorPersonal");
				return criteriaBuilder.equal(gestorPersonalSolicitudPermiso.get("id"), idGestorPersonal);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> hasIdGPAsignado(Long idGPAsignado) {
		return (root, query, criteriaBuilder) -> {
			if (idGPAsignado == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, UsuarioEntity> gpAsignadoSolicitudPermiso = root
						.join("gestorPersonalAsignado");
				return criteriaBuilder.equal(gpAsignadoSolicitudPermiso.get("id"), idGPAsignado);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> hasIdEstadoTramite(Long idEstadoTramite) {
		return (root, query, criteriaBuilder) -> {
			if(idEstadoTramite == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, EstadoTramiteEntity> estadoTramiteSolicitudPermiso = root
						.join("estadoTramite");
				return criteriaBuilder.equal(estadoTramiteSolicitudPermiso.get("id"), idEstadoTramite);
			}
		};
	}
	
	public static Specification<SolicitudPermisoEntity> hasMotivoDenegacion(String motivoDenegacion) {
		return (root, query, criteriaBuilder) -> motivoDenegacion == null || motivoDenegacion.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("motivoDenegacion"), motivoDenegacion);
	}

	public static Specification<SolicitudPermisoEntity> hasFechaSolicitud(Date fechaSolicitud) {
		return (root, query, criteriaBuilder) -> fechaSolicitud == null || fechaSolicitud.equals("")
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaSolicitud"), fechaSolicitud);
	}
	
	
	public static Specification<SolicitudPermisoEntity> hasFechaValidacion(Date fechaValidacion) {
		return (root, query, criteriaBuilder) -> fechaValidacion == null || fechaValidacion.equals("")
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaValidacion"), fechaValidacion);
	}

	public static Specification<SolicitudPermisoEntity> hasFechaAutorizacion(Date fechaAutorizacion) {
		return (root, query, criteriaBuilder) -> fechaAutorizacion == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaAutorizacion"), fechaAutorizacion);
	}

	public static Specification<SolicitudPermisoEntity> hasFechaGestorPersonal(Date fechaGestorPersonal) {
		return (root, query, criteriaBuilder) -> fechaGestorPersonal == null || fechaGestorPersonal.equals("")
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaGestorPersonal"), fechaGestorPersonal);
	}

	public static Specification<SolicitudPermisoEntity> hasFechaPeticionDocumentacion(
			Date fechaPeticionDocumentacion) {
		return (root, query,
				criteriaBuilder) -> fechaPeticionDocumentacion == null || fechaPeticionDocumentacion.equals("")
						? criteriaBuilder.conjunction()
						: criteriaBuilder.equal(root.get("fechaPeticionDocumentacion"), fechaPeticionDocumentacion);
	}


	public static Specification<SolicitudPermisoEntity> hasFechaDesestimacion(
			Date fechaFechaDesestimacion) {
		return (root, query,
				criteriaBuilder) -> fechaFechaDesestimacion == null || fechaFechaDesestimacion.equals("")
						? criteriaBuilder.conjunction()
						: criteriaBuilder.equal(root.get("fechaFechaDesestimacion"), fechaFechaDesestimacion);
	}
	
	public static Specification<SolicitudPermisoEntity> hasFechaDenegacion(Date fechaDenegacion) {
		return (root, query, criteriaBuilder) -> fechaDenegacion == null || fechaDenegacion.equals("")
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaDenegacion"), fechaDenegacion);
	}

//	public static Specification<SolicitudPermisoEntity> hasIdFirma(Long idFirma) {
//        return (root, query, criteriaBuilder) -> idFirma == null  ? criteriaBuilder.conjunction() :  criteriaBuilder.equal(root.get("motivoDenegacion"), idFirma);
//    }

	public static Specification<SolicitudPermisoEntity> hasIdFirma(Long idFirma) {
		return (root, query, criteriaBuilder) -> {
			if (idFirma == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, SolicitudPermisoFirmaEntity> firmaSolicitudPermiso = root.join("firma");
				return criteriaBuilder.equal(firmaSolicitudPermiso.get("id"), idFirma);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> hasIdPermiso(Long idPermiso) {
		return (root, query, criteriaBuilder) -> {
			if (idPermiso == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, SolicitudPermisoFirmaEntity> solicitudPermiso = root.join("permiso");
				return criteriaBuilder.equal(solicitudPermiso.get("id"), idPermiso);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> hasObservacionesValidador(String observacionesValidador) {
		return (root, query, criteriaBuilder) -> observacionesValidador == null || observacionesValidador.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("observacionesValidador"), observacionesValidador);
	}

	public static Specification<SolicitudPermisoEntity> hasObservacionesAutorizador(
			String observacionesAutorizador) {
		return (root, query, criteriaBuilder) -> observacionesAutorizador == null || observacionesAutorizador.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("observacionesAutorizador"), observacionesAutorizador);
	}

	public static Specification<SolicitudPermisoEntity> hasEjercicio(Long ejercicio) {
		return (root, query, criteriaBuilder) -> ejercicio == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("ejercicio"), ejercicio);
	}

	public static Specification<SolicitudPermisoEntity> hasObservacionesParaUsuario(
			String observacionesParaUsuario) {
		return (root, query, criteriaBuilder) -> observacionesParaUsuario == null || observacionesParaUsuario.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("observacionesParaUsuario"), observacionesParaUsuario);
	}

	public static Specification<SolicitudPermisoEntity> hasObservacionesGp(String observacionesGp) {
		return (root, query, criteriaBuilder) -> observacionesGp == null || observacionesGp.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("observacionesGp"), observacionesGp);
	}

	public static Specification<SolicitudPermisoEntity> hasInfoParaGp(String infoParaGp) {
		return (root, query, criteriaBuilder) -> infoParaGp == null || infoParaGp.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("infoParaGp"), infoParaGp);
	}

	public static Specification<SolicitudPermisoEntity> hasIdTipoOperacion(Long idTipoOperacion) {
		return (root, query, criteriaBuilder) -> {
			if(idTipoOperacion == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, TipoOperacionEntity> tipoOperacionEntitySolicitudPermiso = root
						.join("tipoOperacion");
				return criteriaBuilder.equal(tipoOperacionEntitySolicitudPermiso.get("id"), idTipoOperacion);
			}
		};
	}
	
	public static Specification<SolicitudPermisoEntity> hasIdSolicitudAnular(Long idSolicitudAnular) {
		return (root, query, criteriaBuilder) -> {
			if(idSolicitudAnular == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, PermisoEntity> anulacionPermisoEntitySolicitudPermiso = root
						.join("permiso");
				return criteriaBuilder.equal(anulacionPermisoEntitySolicitudPermiso.get("id"), idSolicitudAnular);
			}
		};
	}
	
	
	public static Specification<SolicitudPermisoEntity> hasIdAmbito(Long idAmbito) {
		return (root, query, criteriaBuilder) -> {
			if (idAmbito == null) {
				return criteriaBuilder.conjunction();
			} else {
				Join<SolicitudPermisoEntity, AmbitoEntity> ambitoSolicitudPermiso = root.join("ambito");
				return criteriaBuilder.equal(ambitoSolicitudPermiso.get("id"), idAmbito);
			}
		};
	}

	public static Specification<SolicitudPermisoEntity> isActivo(Boolean activo) {
		return (root, query, criteriaBuilder) -> activo == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("activo"), activo);
	}

	public static Specification<SolicitudPermisoEntity> hasFechaBaja(Date fechaBaja) {
		return (root, query, criteriaBuilder) -> fechaBaja == null ? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaBaja"), fechaBaja);
	}

}
