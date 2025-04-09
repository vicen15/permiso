package es.sgad.trama.permiso.domain.usuario.usuarioEdificioCalendarioFestivo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEdificioCalendarioFestivoDTO {

	private String id;
	
	private String idUsuario;
	
	private String idCalendarioFestivo;
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
		
}
