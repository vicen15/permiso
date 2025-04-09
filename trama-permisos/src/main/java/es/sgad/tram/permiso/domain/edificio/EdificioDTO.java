package es.sgad.trama.permiso.domain.edificio;

import java.util.List;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.calendario.calendarioFestivo.CalendarioFestivoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdificioDTO {

	private String id;

	private String idAmbito;

	private String direccion;

	private String descripcion;

//	private Boolean activo;

	// Esto deberia ser una FK a la tabla de Provincias
	private Long idProvincia;

	// Esto deberia ser una FK a la tabla de Municipio
	private Long idMunicipio;

	private Long idHusoHorario;

	// private Date fechaBaja;

	private List<UsuarioDTO> listaUsuario;

	private List<CalendarioFestivoDTO> listaCalendarioFestivo;

	private List<UsuarioDTO> listaUsuarios;

}