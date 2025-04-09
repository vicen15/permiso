package es.sgad.trama.permiso.domain.pausa;

import java.sql.Date;
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
public class PausaDTO {

    private String id;
	
	private LocalTime horaInicio;
	
	private LocalTime horaFin;
	
	private LocalTime tiempoPausa;
	
	private Integer restarPausa;
	
	private LocalTime tiempoEfectivo;

	private LocalDate fechaBaja;

	private Long idTipoPausa;
		
	private String idTurno;
	
}
