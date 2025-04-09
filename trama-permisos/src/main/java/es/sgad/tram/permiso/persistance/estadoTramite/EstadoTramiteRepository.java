package es.sgad.trama.permiso.persistance.estadoTramite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.estadoTramite.AccionComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.persistance.entity.estadoTramite.EstadoTramiteEntity;

@Repository("permisoEstadoTramiteRepository")
public interface EstadoTramiteRepository extends JpaRepository<EstadoTramiteEntity, Long> {

	@Query("SELECT new es.sgad.trama.permiso.domain.estadoTramite.AccionComboDTO(ete.id, ete.descripcionPantalla) "
			+ "FROM EstadoTramiteEntity ete "
			+ "WHERE ete.fechaBaja is null and ete.estadoFinal = 0 and descripcionPantalla is not null")
	public List<AccionComboDTO> findAccionForCombo();

	@Query("SELECT new es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO(ete.id, ete.descripcionPantalla) "
			+ "FROM EstadoTramiteEntity ete "
			+ "WHERE ete.fechaBaja is null and ete.estadoFinal = 1 order by ete.id")
	public List<EstadoTramiteComboDTO> findEstadoFinalForCombo ();
}
