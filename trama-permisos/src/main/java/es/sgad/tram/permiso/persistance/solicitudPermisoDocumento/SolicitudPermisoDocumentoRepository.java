package es.sgad.trama.permiso.persistance.solicitudPermisoDocumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDocumento.SolicitudPermisoDocumentoEntity;

@Repository
public interface SolicitudPermisoDocumentoRepository extends JpaRepository<SolicitudPermisoDocumentoEntity, String>, JpaSpecificationExecutor<SolicitudPermisoDocumentoEntity>{

}
