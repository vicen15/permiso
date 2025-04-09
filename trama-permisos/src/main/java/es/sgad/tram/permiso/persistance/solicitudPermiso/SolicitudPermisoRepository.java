package es.sgad.trama.permiso.persistance.solicitudPermiso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;

public interface SolicitudPermisoRepository extends JpaRepository<SolicitudPermisoEntity, String> , JpaSpecificationExecutor<SolicitudPermisoEntity> {

}
