package es.sgad.trama.permiso.persistance.firma;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import es.sgad.trama.permiso.persistance.entity.solicitudPermisoFirma.SolicitudPermisoFirmaEntity;

public interface SolicitudPermisoFirmaRepository extends JpaRepository<SolicitudPermisoFirmaEntity, String>, JpaSpecificationExecutor<SolicitudPermisoFirmaEntity>{

}
