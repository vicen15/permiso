package es.sgad.trama.permiso.api.rest.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Informacion del error.
 */
@Getter @Setter @RequiredArgsConstructor @ToString
public class ApiError {
	
	/** Id generado */
	private final String errorId = UUID.randomUUID().toString();
	
	/** Fecha del error */
	private final LocalDateTime timestamp = LocalDateTime.now();
	
	/** Usuario logado cuando se produce el error */
	private final UsuarioDTO usuario;
	
	/** Codigo HTTP */
	private final int status;
	
	/** Descripcion del error HTTP */
	private final String error;
	
	/** Codigo del error */
	private final String code;
	
	/** Mensaje */
	private String message;
	
	/** Ruta del servicio */
	private String path;
	
	/** Stack de la exception */
	private String trace;
	
	/** Errores de validacion */
	private List<ApiValidationError> errors;
	
	
	/**
	 * Incluye un error de validacion.
	 * 
	 * @param field		campo
	 * @param code		codigo
	 * @param message	mensaje
	 */
	public void addValidationError(final String field, final String code, final String message, final UsuarioDTO usuario) {
		if (Objects.isNull(this.errors))
			this.errors = new ArrayList<>();
		
		this.errors.add(new ApiValidationError(field, code, message, usuario));
	}
	
	
	/**
	 * Campos con errores de validacion.
	 */
	@Getter @Setter @AllArgsConstructor @ToString
	public static class ApiValidationError {
		
		/** Id generado */
		private final String Id = UUID.randomUUID().toString();
		
		/** Fecha del error */
		private final LocalDateTime timestamp = LocalDateTime.now();
		
		/** Campo erroneo */
		private final String field;
		
		/** Codigo del error */
		private final String code;
		
		/** Mensaje */
		private String message;
		
		/** Usuario logado cuando se produce el error */
		private final UsuarioDTO usuario;
	}

}
