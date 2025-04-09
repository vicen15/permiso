package es.sgad.trama.permiso.service.incidencia;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IncidenciaService{

	Boolean existenIncidenciasQueSeSolapenConNuevoPermiso(Boolean nuevo_permiso_es_diario, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String idUsuario);
}
