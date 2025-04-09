package es.sgad.trama.permiso.domain.solicitudPermisoDetalle;

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

	private String id;
	
	private String idSolicitudPermiso;
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private LocalTime horaInicio;
	
	private LocalTime horaFin;
	
	private String observaciones;
	
}
