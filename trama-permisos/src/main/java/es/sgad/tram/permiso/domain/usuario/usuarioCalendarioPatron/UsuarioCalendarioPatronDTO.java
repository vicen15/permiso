package es.sgad.trama.permiso.domain.usuario.usuarioCalendarioPatron;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioCalendarioPatronDTO {
	
	private String id;
		
	private String idUsuario;
	
	private String idCalendarioPatron;
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
}