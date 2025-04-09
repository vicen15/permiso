package es.sgad.trama.permiso.domain.turno;

import java.sql.Date;
import java.util.List;

import es.sgad.trama.permiso.domain.calendario.calendarioFestivoDias.CalendarioFestivoDiasDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPatronDias.CalendarioPatronDiasDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPersonalDias.CalendarioPersonalDiasDTO;
import es.sgad.trama.permiso.domain.franja.FranjaDTO;
import es.sgad.trama.permiso.domain.pausa.PausaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoDTO {

	private String id;
	
	private String idAmbito;
	
	private String codigo;
	
	private String descripcion;
	
	private Long turnoVeinticuatroHoras;
	
	private Date tiempo;
	
	private Integer festivo;
	
//	private Integer activo;
	
	private Date fechaBaja; 
	
	private List<CalendarioPatronDiasDTO> listaCalendarioPatronDias;

	private List<FranjaDTO> listaFranjas;
		
	private List<CalendarioFestivoDiasDTO> listaCalendarioFestivoDias;
	
	private List<CalendarioPersonalDiasDTO> listaCalendarioPersonalDias;
	
	private List<PausaDTO> listaPausas;
	
}
