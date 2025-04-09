package es.sgad.trama.permiso.persistance.superior;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.superior.NombreSuperiorDTO;
import es.sgad.trama.permiso.persistance.entity.superior.SuperiorEntity;

/**
 * Repositorio para la entidad {@link SuperiorEntity}, con soporte para Specifications.
 */
@Repository
public interface SuperiorRepository extends JpaRepository<SuperiorEntity, String>, 
                                            JpaSpecificationExecutor<SuperiorEntity> {
	
	
	@Query("SELECT  new es.sgad.trama.permiso.domain.superior.NombreSuperiorDTO(se.suplente.id, se.suplente.nombre, se.suplente.apellido1, se.suplente.apellido2)  "
			+ "FROM SuperiorEntity se "
			+ "WHERE se.usuario.id = :idUsuario")
	public NombreSuperiorDTO findSuplenteForUsuario (
			@Param("idUsuario") UUID idUsuario);
	
	@Query("SELECT  new es.sgad.trama.permiso.domain.superior.NombreSuperiorDTO(se.autorizador.id, se.autorizador.nombre, se.autorizador.apellido1, se.autorizador.apellido2)  "
			+ "FROM SuperiorEntity se "
			+ "WHERE se.usuario.id = :idUsuario")
	public NombreSuperiorDTO findAutorizadorForUsuario (
			@Param("idUsuario") UUID idUsuario);
	
	@Query("SELECT  new es.sgad.trama.permiso.domain.superior.NombreSuperiorDTO(se.validador.id, se.validador.nombre, se.validador.apellido1, se.validador.apellido2)  "
			+ "FROM SuperiorEntity se "
			+ "WHERE se.usuario.id = :idUsuario")
	public NombreSuperiorDTO findValidadorForUsuario (
			@Param("idUsuario") UUID idUsuario);
	

}