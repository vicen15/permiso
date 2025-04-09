//TODO Queda pendiente de añadir a futuro la gestion de incidencias

package es.sgad.trama.permiso.persistance.incidencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.persistance.entity.incidencia.IncidenciaEntity;

/**
 * The Interface AdminIncidenciasRepository.
 */
@Repository
public interface IncidenciaRepository extends JpaRepository<IncidenciaEntity, String>,JpaSpecificationExecutor<IncidenciaEntity> {
    
    String queryContarIncidenciasQueSeSolapenConNuevoPermiso = """
    	    SELECT COUNT(*)
    	    FROM incidencia i
    	    WHERE 
    	        i.id_usuario_solicitante = :idSolicitante
    	        AND (
    	            -- Caso 1: Permiso Diario (es_diario = 1)
    	            (
    	                :nuevo_permiso_es_diario = 1
    	                AND i.fecha BETWEEN :fechaInicio AND :fechaFin
    	            )
    	            -- Caso 2: Permiso Horario (es_diario = 0)
    	            OR (
    	                :nuevo_permiso_es_diario = 0
    	                AND i.fecha = :fechaInicio  -- deben ser del mismo día
    	                AND :horaInicio < i.hora_fin
    	                AND :horaFin > i.hora_inicio
    	            )
    	        )
    	    """;

    @Query(	value = queryContarIncidenciasQueSeSolapenConNuevoPermiso, 
            nativeQuery = true)
    public Long queryContarIncidenciasQueSeSolapenConNuevoPermiso(
    		@Param("nuevo_permiso_es_diario") Integer nuevo_permiso_es_diario,
            @Param("fechaInicio") LocalDate fechaInicio, 
            @Param("fechaFin") LocalDate fechaFin,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin, 
            @Param("idSolicitante") String idSolicitante
    );
    
}
