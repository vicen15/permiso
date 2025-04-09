package es.sgad.trama.permiso.persistance.bandejaPendientes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;

@Repository("FiltroBandejaPermisoPendientesRepository")
public interface FiltroBandejaPendientesRepository extends JpaRepository<SolicitudPermisoEntity, String>, 
JpaSpecificationExecutor<SolicitudPermisoEntity>  {
	
	public static final String consultaPermisosPendientes = 
			"SELECT * FROM SOLICITUD_PERMISO WHERE ID_SOLICITANTE = :idUsuario " //que el solicitante sea al idUsuario 
			+ "AND ID_ESTADO_TRAMITE = 4 AND ID_AMBITO = :idAmbito " +
			"UNION " +
			"SELECT * FROM SOLICITUD_PERMISO WHERE " +
			"ID_SOLICITANTE = (Select ID_USUARIO from SUPERIOR where ID_VALIDADOR = :idUsuario)" + // que el solicitante tenga al idUsuario como validador
			"AND ID_ESTADO_TRAMITE = 1 AND ID_AMBITO = :idAmbito " +
			"UNION " +
			"SELECT * FROM SOLICITUD_PERMISO WHERE " +
			"ID_SOLICITANTE = (Select ID_USUARIO from SUPERIOR where ID_AUTORIZADOR = :idUsuario) " + // que el solicitante tenga al idUsuario como autorizador
			"AND ID_ESTADO_TRAMITE = 2 AND ID_AMBITO = :idAmbito";

	@Query(value=consultaPermisosPendientes, nativeQuery=true)
	public List<SolicitudPermisoEntity> findListaSolicitudPermisoPendiente (
			@Param("idAmbito") String idAmbito,
			@Param("idUsuario") String idUsuario);	
	
	
	
	@Query("SELECT DISTINCT new es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO(spe.solicitante.id, CONCAT(spe.solicitante.nombre, ' ', spe.solicitante.apellido1, ' ', spe.solicitante.apellido2)) "
			+ "FROM SolicitudPermisoEntity spe WHERE "
			+ "(spe.solicitante.id = :idUsuario and spe.estadoTramite.id = 4 AND spe.ambito.id = :idAmbito and spe.solicitante.fechaBaja is null) OR "
			+ "(spe.solicitante.id IN (SELECT distinct se.usuario.id FROM SuperiorEntity se where se.validador.id = :idUsuario) and spe.estadoTramite.id = 1 "
			+ "AND spe.ambito.id = :idAmbito and spe.solicitante.fechaBaja is null) "
			+ "OR (spe.solicitante.id IN (SELECT distinct se.usuario.id FROM SuperiorEntity se where se.autorizador.id = :idUsuario) and spe.estadoTramite.id = 2 "
			+ "and spe.ambito.id = :idAmbito and spe.solicitante.fechaBaja is null)")
	public List<SolicitanteComboDTO> findSolicitanteForCombo (
			@Param("idAmbito") String idAmbito,
			@Param("idUsuario") String idUsuario);

}
