package es.sgad.trama.permiso.domain.ambito;


import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import es.sgad.trama.permiso.domain.calendario.calendarioFestivo.CalendarioFestivoDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioPatron.CalendarioPatronDTO;
import es.sgad.trama.permiso.domain.edificio.EdificioDTO;
import es.sgad.trama.permiso.domain.tipoHora.TipoHoraDTO;
import es.sgad.trama.permiso.domain.turno.TurnoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmbitoDTO {
	
	private String id;
		
	private String nombre;
	
	private Date fechaAlta;
	
	private String usuarioWs;

	private String contraseniaWs;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	private Long firmaSolicitar;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	private Long firmaAutorizar;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	private Long firmaFichaje;
	
	private Date fechaLimiteVacaciones;
	
	private Long cortesiaEntrada;
	
	private Long cortesiaSalida;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	private Long opcionValidarSolicitud;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	private Long opcionValidarFechaLimiteVacaciones;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	private Long opcionLicenciaAAPP;
	
	private Date horaEmpiezaTarde;
	
	private Boolean avisarAusencias;
	
	private Boolean avisarMarcajesImpares;
	
	private Blob plantillaBolsaConciliacion;
	
	private Long numeroPersonasInformeAsincrono;
	
	private Date fechaBaja;
	
	private String observacion;
	
//	private Boolean activo;
	
	private List<TurnoDTO> listaTurnos;
	
	private List<CalendarioPatronDTO> listaCalendarioPatron;
	
	private List<CalendarioFestivoDTO> listaCalendarioFestivo;
	
	private List<EdificioDTO> listaEdificios;
	
	private List<TipoHoraDTO> listaTipoHoras;
	
}