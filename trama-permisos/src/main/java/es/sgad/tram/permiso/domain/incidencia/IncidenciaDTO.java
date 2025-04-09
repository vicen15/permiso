package es.sgad.trama.permiso.domain.incidencia;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenciaDTO{
	
	private String id;
	
	private String idSolicitudIncidencia;
	
	private String idTipoIncidenciaAmbito;
	
	private String idUsuarioSolicitante;
	
	private LocalDate fecha;

	private LocalTime horaInicio;

	private LocalTime horaFin;
	
	private Integer ejercicio;
}

