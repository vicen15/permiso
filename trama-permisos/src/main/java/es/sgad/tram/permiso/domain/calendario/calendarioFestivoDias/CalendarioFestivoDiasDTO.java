package es.sgad.trama.permiso.domain.calendario.calendarioFestivoDias;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioFestivoDiasDTO {

	private String id;
	
	private String idTurno;
	
	private String idCalendarioFestivo;
	
	private Date fecha;
		
//	private Boolean activo;
//	
//	private LocalDateTime fechaBaja;
	
}
