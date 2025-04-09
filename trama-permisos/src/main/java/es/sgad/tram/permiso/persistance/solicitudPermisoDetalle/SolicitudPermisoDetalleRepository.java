package es.sgad.trama.permiso.persistance.solicitudPermisoDetalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;

public interface SolicitudPermisoDetalleRepository extends JpaRepository<SolicitudPermisoDetalleEntity, String>, JpaSpecificationExecutor<SolicitudPermisoDetalleEntity>  {

}
