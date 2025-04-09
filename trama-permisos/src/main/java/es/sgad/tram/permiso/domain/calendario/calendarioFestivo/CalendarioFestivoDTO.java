package es.sgad.trama.permiso.domain.calendario.calendarioFestivo;

import java.sql.Date;
import java.util.List;

import es.sgad.trama.permiso.domain.calendario.calendarioFestivoDias.CalendarioFestivoDiasDTO;
import es.sgad.trama.permiso.domain.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioFestivoDTO {

	private String id;
	
	private String idAmbito;
	
	private String idEdificio;
	
	private String nombre;
	
	private Date anio;
	
	private String descripcion;
	
//	private Boolean activo;
//	
//	private LocalDateTime fechaBaja;
	
	private List<CalendarioFestivoDiasDTO> listaCalendarioFestivoDias;
	
	private List<UsuarioEdificioCalendarioFestivoDTO> listaUsuarioEdificioCalendarioFestivo;

}
