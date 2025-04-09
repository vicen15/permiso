package es.sgad.trama.permiso.persistance.tipoOperacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO;
import es.sgad.trama.permiso.persistance.entity.tipoOperacion.TipoOperacionEntity;

public interface TipoOperacionRepository extends JpaRepository<TipoOperacionEntity, Long>, JpaSpecificationExecutor<TipoOperacionEntity>{

	@Query("SELECT new es.sgad.trama.permiso.domain.tipoOperacion.TipoOperacionComboDTO(toe.id, toe.descripcion) "
			+ "FROM TipoOperacionEntity toe")
	public List<TipoOperacionComboDTO> findTipoOperacionForCombo ();
	
}
