package es.sgad.trama.permiso.domain;

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
public class PermisoDTO{
	
	private String id;
	
	private String idUsuarioSolicitante;
	
	private String idTipoPermiso;
	
	private String idSolicitudPermiso;
	
	private LocalDate fechaInicio;

	private LocalDate fechaFin;
	
	private LocalTime horaInicio;

	private LocalTime horaFin;
	
	private Integer ejercicio;
	
	private Integer numeroDias;
	
	private Boolean esDiario;
	
}

