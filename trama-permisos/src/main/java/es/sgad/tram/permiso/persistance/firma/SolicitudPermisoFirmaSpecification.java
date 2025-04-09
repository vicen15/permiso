package es.sgad.trama.permiso.persistance.firma;

import java.sql.Date;

import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.permiso.persistance.entity.solicitudPermisoFirma.SolicitudPermisoFirmaEntity;

public class SolicitudPermisoFirmaSpecification {
	
	public static Specification<SolicitudPermisoFirmaEntity> filterBy(SolicitudPermisoFirmaFilter solicitudPermisoFirmaFilter){
				
		return Specification.where(hasFirmaCriptoSolicitante(solicitudPermisoFirmaFilter.firmaCriptoSolicitante()))
				.and(hasFirmaNoCriptoSolicitante(solicitudPermisoFirmaFilter.firmaNoCriptoSolicitante()))
				.and(hasFirmaCriptoAutorizador(solicitudPermisoFirmaFilter.firmaCriptoAutorizador()))
				.and(hasFirmaNoCriptoAutorizador(solicitudPermisoFirmaFilter.firmaNoCriptoAutorizador()))
				.and(isActivo(solicitudPermisoFirmaFilter.activo())
				.and(hasFechaBaja(solicitudPermisoFirmaFilter.fechaBaja())));
	}
	
	public static Specification<SolicitudPermisoFirmaEntity> hasFirmaCriptoSolicitante(String firmaCriptoSolicitante) {
		return (root, query, criteriaBuilder) -> firmaCriptoSolicitante == null || firmaCriptoSolicitante.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("firmaCriptoSolicitante"), firmaCriptoSolicitante);
	}
	
	public static Specification<SolicitudPermisoFirmaEntity> hasFirmaNoCriptoSolicitante(String firmaNoCriptoSolicitante) {
		return (root, query, criteriaBuilder) -> firmaNoCriptoSolicitante == null || firmaNoCriptoSolicitante.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("firmaNoCriptoSolicitante"), firmaNoCriptoSolicitante);
	}
	
	public static Specification<SolicitudPermisoFirmaEntity> hasFirmaCriptoAutorizador(String firmaCriptoAutorizador) {
		return (root, query, criteriaBuilder) -> firmaCriptoAutorizador == null || firmaCriptoAutorizador.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("firmaCriptoAutorizador"), firmaCriptoAutorizador);
	}
	
	public static Specification<SolicitudPermisoFirmaEntity> hasFirmaNoCriptoAutorizador(String firmaNoCriptoAutorizador) {
		return (root, query, criteriaBuilder) -> firmaNoCriptoAutorizador == null || firmaNoCriptoAutorizador.isEmpty()
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("firmaNoCriptoAutorizador"), firmaNoCriptoAutorizador);
	}
	
	public static Specification<SolicitudPermisoFirmaEntity> isActivo(Boolean activo) {
		return (root, query, criteriaBuilder) -> activo == null
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("activo"), activo);
	}
	
	public static Specification<SolicitudPermisoFirmaEntity> hasFechaBaja(Date fechaBaja) {
		return (root, query, criteriaBuilder) -> fechaBaja == null
				? criteriaBuilder.conjunction()
				: criteriaBuilder.equal(root.get("fechaBaja"), fechaBaja);
	}
}
