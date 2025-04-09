package es.sgad.trama.permiso.domain.calendario.calendarioPatron;



import java.sql.Date;
import java.util.List;

import es.sgad.trama.permiso.domain.calendario.calendarioPatronDias.CalendarioPatronDiasDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioPatronDTO {
	
	private String id;
		
	private String idAmbito;
	
	private Date anio;
	
	private String nombre;

	private String descripcion;
	
//	private Boolean activo;
//	
//	private LocalDateTime fechaBaja;
	
	private List<CalendarioPatronDiasDTO> listaCalendarioPatronDias;
	
}