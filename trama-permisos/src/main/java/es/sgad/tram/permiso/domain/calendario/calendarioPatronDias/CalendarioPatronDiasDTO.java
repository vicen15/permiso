package es.sgad.trama.permiso.domain.calendario.calendarioPatronDias;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioPatronDiasDTO {
	
	private String id;
		
	private String idCalendarioPatron;
	
	private String idTurno;
	
	private Date fecha;
	
//	private Boolean activo;
//	
//	private LocalDateTime fechaBaja;

}