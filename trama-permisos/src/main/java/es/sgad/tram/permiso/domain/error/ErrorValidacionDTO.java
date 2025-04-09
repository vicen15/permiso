package es.sgad.trama.permiso.domain.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorValidacionDTO {
	
    private String id;
	
	private String errorId;
	
	private LocalDateTime fechaHora;
	
	private String usuario;
	
	private String campo;
	
	private String codigo;
	
	private String mensaje;
		
}