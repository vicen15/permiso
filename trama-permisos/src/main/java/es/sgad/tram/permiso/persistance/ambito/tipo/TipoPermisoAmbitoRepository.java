
package es.sgad.trama.permiso.persistance.ambito.tipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;

/**
 * The Interface AdminPermisosRepository.
 */
@Repository
public interface TipoPermisoAmbitoRepository extends JpaRepository<TipoPermisoAmbitoEntity, String>,JpaSpecificationExecutor<TipoPermisoAmbitoEntity> {
					   
	@Query("SELECT new es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO(tie.id, tie.descripcion) "
			+ "FROM TipoPermisoAmbitoEntity tie "
			+ "WHERE tie.ambito = :ambito AND tie.fechaBaja is null")
	public List<TipoPermisoAmbitoComboDTO> findTipoPermisoAmbitoForCombo (@Param("ambito") AmbitoEntity ambito);

	@Query("SELECT tiae from TipoPermisoAmbitoEntity tiae WHERE tiae.ambito.id = :idAmbito")
	List<TipoPermisoAmbitoEntity> findTipoPermisoAmbitoByIdAmbito(@Param("idAmbito") String idAmbito);
	
	//TODO filtrar por idTipo, IdAmbito
	@Query("SELECT tiae from TipoPermisoAmbitoEntity tiae WHERE tiae.ambito.id = :idAmbito and tiae.tipoPermiso.id = :idTipoPermiso")
	List<TipoPermisoAmbitoEntity> findTipoPermisoAmbitoByIdAmbitoAndIdTipoPermiso(@Param("idAmbito") String idAmbito, @Param("idTipoPermiso") String idTipoPermiso);	
	
}
