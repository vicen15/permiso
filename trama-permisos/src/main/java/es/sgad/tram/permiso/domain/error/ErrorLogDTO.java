package es.sgad.trama.permiso.domain.error;


import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLogDTO {
	
	private String id;
	
	private LocalDateTime fechaHora;
	
	private String usuario;
	
	private Integer status;
	
	private String descripcion;
	
	private String codigo;
	
	private String mensaje;
	
	private String ruta;
	
	private String pila;
	
	// Listado de las validaciones
	private List<ErrorValidacionDTO> listaValidaciones;
	
}