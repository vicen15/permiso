package es.sgad.trama.permiso.persistance.bandejaEnviados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;

@Repository
public interface FiltroBandejaEnviadosRepository extends JpaRepository<SolicitudPermisoEntity, String>, 
JpaSpecificationExecutor<SolicitudPermisoEntity>  {
	
	// Sql solicitudes de permisos enviados
		@Query("SELECT spe FROM SolicitudPermisoEntity spe "
				// solicitud => solicitante yo -> solicitud creada por mi
				+ "WHERE (spe.solicitante.id = :idUsuario AND spe.solicitante.fechaBaja is null) AND spe.estadoTramite.fechaBaja is null AND "
				+ "spe.ambito.id = :idAmbito")
	public List<SolicitudPermisoEntity> findListaSolicitudPermisoEnviado (
			@Param("idAmbito") String idAmbito,
			@Param("idUsuario") String idUsuario);
	
/*	@Query("SELECT distinct new es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO(sie.solicitante.id, CONCAT(sie.solicitante.nombre, ' ', sie.solicitante.apellido1, ' ', sie.solicitante.apellido2)) "
			+ "FROM SolicitudIncidenciaEntity sie "
			// solicitante yo => solicitante yo -> solicitud creada por mi
			+ "WHERE (sie.solicitante.id = :idUsuario AND sie.solicitante.fechaBaja is null) AND sie.estadoTramite.fechaBaja is null AND "
			+ "sie.ambito.id = :idAmbito")
	public List<SolicitanteComboDTO> findSolictanteForComboEntrada (
			@Param("idAmbito") String idAmbito,
			@Param("idUsuario") String idUsuario);*/
	
	@Query("SELECT distinct new es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO(spe.solicitante.id, CONCAT(spe.solicitante.nombre, ' ', spe.solicitante.apellido1, ' ', spe.solicitante.apellido2)) "
			+ "FROM SolicitudPermisoEntity spe "
			// solicitante yo => solicitante yo -> solicitud creada por mi
			+ "WHERE ((spe.solicitante.id = :idUsuario AND spe.solicitante.fechaBaja is null) OR "
			// solicitante xx => validador yo   -> solicitud validada por mi
			+ "(spe.validador.id = :idUsuario AND spe.validador.fechaBaja is null AND (spe.fechaValidacion is not null OR spe.fechaDenegada is not null)) OR "
			// solicitante yy => autorizador yo -> solicitud autorizada por mi
			+ "(spe.autorizador.id = :idUsuario AND spe.autorizador.fechaBaja is null AND (spe.fechaAutorizacion is not null OR spe.fechaDenegada is not null))) AND "
			+ "spe.ambito.id = :idAmbito")
	public List<SolicitanteComboDTO> findSolicitanteForCombo (
			@Param("idAmbito") String idAmbito,
			@Param("idUsuario") String idUsuario);

}
