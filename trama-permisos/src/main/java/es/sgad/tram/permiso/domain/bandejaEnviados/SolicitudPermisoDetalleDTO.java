package es.sgad.trama.permiso.domain.bandejaEnviados;

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
public class SolicitudPermisoDetalleDTO {
	
	/** Identificador del detalle del permiso */
	private String id;
	
	/** Fecha de inicio del permiso */
	private LocalDate fechaInicio;
	
	/** Fecha de fin del permiso */
	private LocalDate fechaFin;	
	
	/** Hora de inicio del permiso */
	private LocalTime horaInicio;
	
	/** Hora en la que termina el permiso */
	private LocalTime horaFin;
	
	/** observaciones */
	private String Observaciones;
	
}
