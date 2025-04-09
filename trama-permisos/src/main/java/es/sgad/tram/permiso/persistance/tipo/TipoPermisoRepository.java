
package es.sgad.trama.permiso.persistance.tipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.tipo.TipoPermisoComboDTO;
import es.sgad.trama.permiso.persistance.entity.tipo.TipoPermisoEntity;

/**
 * The Interface AdminIncidenciasRepository.
 */
@Repository
public interface TipoPermisoRepository extends JpaRepository<TipoPermisoEntity, Long>,JpaSpecificationExecutor<TipoPermisoEntity> {
	
	@Query("SELECT new es.sgad.trama.permiso.domain.tipo.TipoPermisoComboDTO(tie.id, tie.descripcion) "
			+ "FROM TipoPermisoEntity tie "
			+ "WHERE tie.fechaBaja is null")
	public List<TipoPermisoComboDTO> findTipoPermisoForCombo ();

}
