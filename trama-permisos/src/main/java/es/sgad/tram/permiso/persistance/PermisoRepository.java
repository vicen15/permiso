//TODO Queda pendiente el correcto repositorio de Permisos si es necesario

package es.sgad.trama.permiso.persistance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.domain.PermisoDTO;
import es.sgad.trama.permiso.persistance.entity.PermisoEntity;

/**
 * The Interface PermisoRepository.
 */
@Repository
public interface PermisoRepository extends JpaRepository<PermisoEntity, String>,JpaSpecificationExecutor<PermisoEntity> {

	/**
	 * Obtenermos la lista de Permisos con los datos de
	 * fechas y el idUsuario.
	 * 
	 * @param idUsuario
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@Query("SELECT new es.sgad.trama.permiso.domain.PermisoDTO(" +
		       "p.id, p.usuario.id, p.idTipoPermiso, p.solicitudPermiso, p.fechaInicio, p.fechaFin, " +
		       "p.horaInicio, p.horaFin, p.ejercicio, p.numeroDias, p.esDiario) " +
		       "FROM PermisoEntity p WHERE p.usuario.id = :idUsuario " +
		       "AND p.fechaInicio >= :fechaInicio " +
		       "AND p.fechaFin <= :fechaFin")
	List<PermisoDTO> findByUsuarioIdAndFechaRango(
			@Param("idUsuario") String idUsuario,
			@Param("fechaInicio") LocalDate fechaInicio,
			@Param("fechaFin") LocalDate fechaFin);
	
	
	String queryContarPermisosEntreFechasPorUsuario = """
		    SELECT COUNT(*)
		    FROM permiso p
		    WHERE p.id_usuario_solicitante = :idSolicitante
		        AND (
		            -- Caso 1: Permisos Diarios (ES_DIARIO = 1)
		            (
		                p.es_diario = CASE WHEN :nuevoPermisoEsDiario = 1 THEN 1 ELSE 0 END
		                AND :fechaInicio <= p.fecha_fin
		                AND :fechaFin >= p.fecha_inicio
		            )
		            -- Caso 2: Permisos Horarios (ES_DIARIO = 0)
		            OR (
		                p.es_diario = CASE WHEN :nuevoPermisoEsDiario = 0 THEN 0 ELSE 1 END
		                AND p.fecha_inicio = :fechaInicio  -- mismo día
		                AND :horaInicio < p.hora_fin
		                AND :horaFin > p.hora_inicio
		            )
		        )
		    """;

		@Query(value = queryContarPermisosEntreFechasPorUsuario, nativeQuery = true)
		public Long contarPermisosEntreFechasPorUsuario(
			@Param("nuevoPermisoEsDiario") Integer nuevoPermisoEsDiario,
		    @Param("fechaInicio") LocalDate fechaInicio, 
		    @Param("fechaFin") @Nullable LocalDate fechaFin,  
		    @Param("horaInicio") @Nullable LocalTime horaInicio,  
		    @Param("horaFin") @Nullable LocalTime horaFin,  
		    @Param("idSolicitante") String idSolicitante
		);


}
