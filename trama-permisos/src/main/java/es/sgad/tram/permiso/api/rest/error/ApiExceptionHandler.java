package es.sgad.trama.permiso.api.rest.error;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.sgad.trama.common.configuracion.ConfiguracionUtil;
import es.sgad.trama.common.configuracion.Propiedad;
import es.sgad.trama.common.exception.AbstractTramaException;
import es.sgad.trama.common.exception.FirmaException;
import es.sgad.trama.common.exception.ItemFoundException;
import es.sgad.trama.common.exception.ItemNotFoundException;
import es.sgad.trama.common.exception.ValidationException;
import es.sgad.trama.common.idioma.IdiomaUtil;
import es.sgad.trama.common.idioma.MensajeError;
import es.sgad.trama.common.utils.SeguridadUtil;
import es.sgad.trama.permiso.api.rest.error.ApiError.ApiValidationError;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import es.sgad.trama.permiso.domain.error.ErrorValidacionDTO;
import es.sgad.trama.permiso.service.error.ErrorLogService;
import es.sgad.trama.permiso.service.error.ErrorValidacionService;
import es.sgad.trama.permiso.service.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;

/**
 * Handler encargado de capturar las excepciones y enviar el error correspondiente.
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ErrorLogService errorLogService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ErrorValidacionService errorValidacionService;
	

	
	/**
	 * Trata las excepciones {@link ItemNotFoundException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Object> handleItemNotFoundException(final ItemNotFoundException ex, final WebRequest request) {
		return this.buildApiErrorResponse(ex, ex.getCodigo(), ex.getParametros(), HttpStatus.NOT_FOUND, request);
	}
	
	/**
	 * Trata las excepciones {@link ItemFoundException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(ItemFoundException.class)
	public ResponseEntity<Object> handleItemFoundException(final ItemFoundException ex, final WebRequest request) {
		return this.buildApiErrorResponse(ex, ex.getCodigo(), ex.getParametros(), HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Trata las excepciones {@link ValidationException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleValidationException(final ValidationException ex, final WebRequest request) {
		return this.buildApiValidationErrorResponse(ex, ex.getErrores().getFieldErrors(), HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Trata las excepciones {@link AbstractTramaException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(AbstractTramaException.class)
	public ResponseEntity<Object> handleAbstractTramaException(final AbstractTramaException ex, final WebRequest request) {
		return this.buildApiErrorResponse(ex, ex.getCodigo(), ex.getParametros(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	/**
	 * Trata las excepciones {@link BadCredentialsException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialsException(final BadCredentialsException ex, final WebRequest request) {
		String code = MensajeError.EXCEPTION_BAD_CREDENTIALS.getCodigo();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	
	/**
	 * Trata las excepciones {@link AccessDeniedException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request) {
		String code = MensajeError.EXCEPTION_ACCESS_DENIED.getCodigo();
		HttpStatus status = HttpStatus.FORBIDDEN;
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	
	/**
	 * Trata las excepciones {@link DataIntegrityViolationException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(final DataIntegrityViolationException ex, final WebRequest request) {
		String code = MensajeError.EXCEPTION_DATA_INTEGRITY_VIOLATION.getCodigo();
		HttpStatus status = HttpStatus.CONFLICT;
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	
	/**
	 * Trata las excepciones {@link ConstraintViolationException}.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {
		return this.buildApiValidationErrorResponse(ex, ex.getConstraintViolations(), HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Trata todas las excepciones que no tengan un tratamiento especifico.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
		String code = MensajeError.EXCEPTION_INTERNAL_SERVER_ERROR.getCodigo();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	/**
	 * Trata todas las excepciones de tipo FirmaException.
	 * 
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return respuesta
	 */
	@ExceptionHandler(FirmaException.class)
	public ResponseEntity<Object> handleFirmaException(final Exception ex, final WebRequest request) {
		String code = MensajeError.EXCEPTION_INTERNAL_SERVER_ERROR.getCodigo();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_METHOD_NOT_SUPPORTED.getCodigo();
		Collection<Object> params = List.of(
				// Metodo
				ex.getMethod(),
				// Metodos permitidos
				ex.getSupportedHttpMethods().stream().map(Object::toString).collect(Collectors.joining(", ")));
		
		return this.buildApiErrorResponse(ex, code, params, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_UNSUPPORTED_MEDIA_TYPE.getCodigo();
		Collection<Object> params = List.of(
				// Tipo
				ex.getContentType(),
				// Tipos permitidos
				ex.getSupportedMediaTypes().stream().map(Object::toString).collect(Collectors.joining(", ")));
		
		return this.buildApiErrorResponse(ex, code, params, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_MEDIA_TYPE_NOT_ACCEPTABLE.getCodigo();
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_MISSING_PATH_VARIABLE.getCodigo();
		Collection<Object> params = List.of(
				// Nombre
				ex.getVariableName());
		
		return this.buildApiErrorResponse(ex, code, params, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_MISSING_REQUEST_PARAMETER.getCodigo();
		Collection<Object> params = List.of(
				// Nombre
				ex.getParameterName());
		
		return this.buildApiErrorResponse(ex, code, params, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_REQUEST_BINDING.getCodigo();
		Collection<Object> params = new ArrayList<Object>();
		
		// Nombre
		Pattern pattern = Pattern.compile("'(.*)'");
		Matcher matcher = pattern.matcher(ex.getMessage());
		if (matcher.find())
			params.add(matcher.group(0));
		
		return this.buildApiErrorResponse(ex, code, params, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_CONVERSION_NOT_SUPPORTED.getCodigo();
		Collection<Object> params = List.of(
				// Valor
				ex.getValue().getClass().getSimpleName(),
				// Tipo
				ex.getRequiredType().getSimpleName());
		
		return this.buildApiErrorResponse(ex, code, params, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_TYPE_MISMATCH.getCodigo();
		Collection<Object> params = List.of(
				// Valor
				ex.getValue().getClass().getSimpleName(),
				// Tipo
				ex.getRequiredType().getSimpleName());
		
		return this.buildApiErrorResponse(ex, code, params, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_MESSAGE_NOT_READABLE.getCodigo();
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_MESSAGE_NOT_WRITABLE.getCodigo();
		
		return this.buildApiErrorResponse(ex, code, null, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    return this.buildApiValidationErrorResponse(ex, ex.getFieldErrors(), status, request);
	}

	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_MISSING_REQUEST_PART.getCodigo();
		Collection<Object> params = List.of(
				// Nombre
				ex.getRequestPartName());
		
		return this.buildApiErrorResponse(ex, code, params, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return this.buildApiValidationErrorResponse(ex, ex.getFieldErrors(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String code = MensajeError.EXCEPTION_NO_HANDLER_FOUND.getCodigo();
		Collection<Object> params = List.of(
				// Metodo
				ex.getHttpMethod(),
				// URL
				ex.getRequestURL());
		
		return this.buildApiErrorResponse(ex, code, params, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return this.buildApiErrorResponse(ex, MensajeError.EXCEPTION_INTERNAL_SERVER_ERROR.getCodigo(), null, status, request);
	}
	
	/**
	 * Devuelve una respuesta con el error en funcion de la excepcion.
	 * 
	 * @param ex		excepcion
	 * @param code		codigo de error
	 * @param params	parametros
	 * @param status	estado HTTP
	 * @param request	peticion
	 * @return respuesta
	 */
	private ResponseEntity<Object> buildApiErrorResponse(final Exception ex, final String code, final Collection<Object> params, final HttpStatus status, final WebRequest request) {
				
		// Genera el error
		ApiError error = this.getApiError(ex, code, params, status, request);
		
		//Escribimos el log
		log.error("Error ID: {}, código: {}, mensaje: {}, usuario: {}", error.getErrorId(), code, ex.getMessage(), ex, request.getRemoteUser());
		
		// Convertimos el ApiError a ErrorLogDTO
		ErrorLogDTO errorDTO = convertirApiErrorAErrorLogDTO(error);
		
		// Guardamos el error en BBDD
		errorLogService.saveErrorLog(errorDTO);
		
		return this.buildApiResponse(ex, status, error);
	}
	
	/**
	 * Devuelve una respuesta con los errores de validacion.
	 * 
	 * @param ex		excepcion
	 * @param errors	errores de validacion
	 * @param status	estado HTTP
	 * @param request	peticion
	 * @return respuesta
	 */
	private ResponseEntity<Object> buildApiValidationErrorResponse(final Exception ex, final Collection<FieldError> errors, final HttpStatus status, final WebRequest request) {
		String code = MensajeError.VALIDACION.getCodigo();
		
		// Genera el error
		ApiError error = this.getApiError(ex, code, null, status, request);
		
		//Escribimos el log
		log.error("Error ID: {}, código: {}, mensaje: {}, usuario: {}", error.getErrorId(), code, ex.getMessage(), ex, request.getRemoteUser());
		
		// Convertimos el ApiError a ErrorLogDTO
		ErrorLogDTO errorDTO = convertirApiErrorAErrorLogDTO(error);
		
		// Guardamos el error en BBDD
		errorLogService.saveErrorLog(errorDTO);
				
		// Se incluyen los errores de validacion
	/*	if (Objects.nonNull(errors))
			errors.forEach(e -> error.addValidationError(e.getField(), e.getCode(), IdiomaUtil.getMensaje(e.getCode(), e.getArguments())));*/
		
		// Se incluyen los errores de validacion
		if (Objects.nonNull(errors)) {
			
			Iterator <FieldError> it = errors.iterator();
			FieldError errorVal;
			
			// Se crea la lista de errores de validación del error principal y se escriben en el log
			while(it.hasNext()){
				errorVal = it.next();
				
				//TODO pendiente de ver como llegara la informacion en el token para de alli sacar los datos del usuario
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String userDetails = auth.getDetails().toString();
				
				UsuarioDTO usuario = null;
				
				if(StringUtils.isNotBlank(userDetails)) {
					//Buscamos el usaurio logado
					usuario = usuarioService.getByDocIdent(SeguridadUtil.extraerDocIdentFromRequestRemoteUser(userDetails));
				}
				
				// Guardamos en la lista el error de validación
				error.addValidationError(errorVal.getField(), errorVal.getCode(), IdiomaUtil.getMensaje(errorVal.getCode(),errorVal.getArguments()), usuario);
				//Escribimos el log
				log.error("Error Campo: {}, código: {}, mensaje: {}, usuario: {}", errorVal.getField(), errorVal.getCode(), IdiomaUtil.getMensaje(errorVal.getCode(),errorVal.getArguments()), request.getRemoteUser());
				
			}
				
			Iterator <ApiValidationError> itError = error.getErrors().iterator();
			ApiValidationError apiValidationError;
			
			// Se guardan en bbdd los errores de la lista de errores de validación
			while(itError.hasNext()){
				
				apiValidationError = itError.next();
				// Convertimos el ApiValidationError a ErrorValidacionDTO
				ErrorValidacionDTO errorValidacionDTO = convertirApiValidationErrorAErrorValidacionDTO(apiValidationError, error.getErrorId());
				// Guardamos el error de validación en BBDD
				errorValidacionService.saveErrorValidacion(errorValidacionDTO);
				
			}
		}
			 
		return this.buildApiResponse(ex, status, error);
	}
//	private ResponseEntity<Object> buildApiValidationErrorResponse(
//		    final Exception ex, final Collection<FieldError> errors, final HttpStatusCode status, final WebRequest request) {
//		    String code = MensajeError.VALIDACION.getCodigo();
//
//		    // Genera el error
//		    ApiError error = this.getApiError(ex, code, null, status, request);
//
//		    // Se incluyen los errores de validación
//		    if (Objects.nonNull(errors))
//		        errors.forEach(e -> error.addValidationError(
//		            e.getField(),
//		            e.getCode(),
//		            IdiomaUtil.getMensaje(e.getCode(), e.getArguments())
//		        ));
//
//		    return this.buildApiResponse(ex, status, error);
//	}

	
	/**
	 * Devuelve una respuesta con los errores de validacion.
	 * 
	 * @param ex		excepcion
	 * @param errors	errores de validacion
	 * @param status	estado HTTP
	 * @param request	peticion
	 * @return respuesta
	 */
	private ResponseEntity<Object> buildApiValidationErrorResponse(final Exception ex, final Set<ConstraintViolation<?>> errors, final HttpStatus status, final WebRequest request) {
		String code = MensajeError.VALIDACION.getCodigo();
		
		// Genera el error
		ApiError error = this.getApiError(ex, code, null, status, request);
		
		//Escribimos el log
		log.error("Error ID: {}, código: {}, mensaje: {}, usuario: {}", error.getErrorId(), code, ex.getMessage(), ex, request.getRemoteUser());
		
		// Convertimos el ApiError a ErrorLogDTO
		ErrorLogDTO errorDTO = convertirApiErrorAErrorLogDTO(error);
		
		// Guardamos el error en BBDD
		errorLogService.saveErrorLog(errorDTO);
				
		// Se incluyen los errores de validacion
		/*		if (Objects.nonNull(errors))
					errors.forEach(e -> error.addValidationError(e.getPropertyPath().toString(), e.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(), e.getMessage()));*/
		
		// Se incluyen los errores de validacion
		if (Objects.nonNull(errors)) {
			
			Iterator <ConstraintViolation<?>> it = errors.iterator();
			ConstraintViolation<?> errorVal;
			
			// Se crea la lista de errores de validación del error principal y se escriben en el log
			while(it.hasNext()){
				errorVal = it.next();
				
				//Buscamos el usaurio logado
				UsuarioDTO usuario = usuarioService.getByDocIdent(SeguridadUtil.extraerDocIdentFromRequestRemoteUser(request.getRemoteUser()));
				
				// Guardamos en la lista el error de validación
				error.addValidationError(errorVal.getPropertyPath().toString(), errorVal.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(), errorVal.getMessage(), usuario);
				//Escribimos el log
				log.error("Error Constraint: {}, valor no válido: {}, mensaje: {}, usuario: {}", errorVal.getConstraintDescriptor(), errorVal.getInvalidValue(), errorVal.getMessage(), request.getRemoteUser());
			}
				
			Iterator <ApiValidationError> itError = error.getErrors().iterator();
			ApiValidationError apiValidationError;
			
			// Se guardan en bbdd los errores de la lista de errores de validación
			while(itError.hasNext()){
				
				apiValidationError = itError.next();
				// Convertimos el ApiValidationError a ErrorValidacionDTO
				ErrorValidacionDTO errorValidacionDTO = convertirApiValidationErrorAErrorValidacionDTO(apiValidationError, error.getErrorId());
				// Guardamos el error de validación en BBDD
				errorValidacionService.saveErrorValidacion(errorValidacionDTO);
				
			}
		}
		
		
		return this.buildApiResponse(ex, status, error);
	}
	
	/**
	 * Devuelve una respuesta con el error.
	 * 
	 * @param ex		excepcion
	 * @param status	estado HTTP
	 * @param error		error
	 * @return respuesta
	 */
	private ResponseEntity<Object> buildApiResponse(final Exception ex, final HttpStatus status, final ApiError error) {
		log.error("Se ha producido un error: {}", error);
		log.error("Excepcion: {}", ex);
		
		return ResponseEntity.status(status).body(error);
	}
	
	/**
	 * Genera un {@link ApiError} con los datos de la excepcion.
	 * 
	 * @param ex		excepcion
	 * @param code		codigo de error
	 * @param params	parametros
	 * @param status	estado HTTP
	 * @param request	peticion
	 * @return error
	 */
	private ApiError getApiError(final Exception ex, final String code, final Collection<Object> params, final HttpStatus status, final WebRequest request) {
		//TODO pendiente de ver como llegara la informacion en el token para de alli sacar los datos del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userDetails = auth.getDetails().toString();
		
		UsuarioDTO usuario = null;
		
		if(StringUtils.isNotBlank(userDetails)) {
			usuario = usuarioService.getByDocIdent(SeguridadUtil.extraerDocIdentFromRequestRemoteUser(userDetails));
		}

		// Genera el error
		ApiError error = new ApiError(usuario, status.value(), String.valueOf(status.value()), code);
		
		// Obtiene el mensaje
		error.setMessage(IdiomaUtil.getMensaje(code, Objects.isNull(params) ? null : params.toArray(Object[]::new)));
		
		// Obtiene la ruta del servicio web
		error.setPath(getPath(request));
		
		// Obtiene el stack
		error.setTrace(getStackTrace(ex, request));
		
		return error;
	}

	/**
	 * @param request	peticion
	 * @return ruta del servicio web
	 */
	private static String getPath(final WebRequest request) {
		if (request instanceof ServletWebRequest swr)
			return swr.getRequest().getRequestURI();
		
		return null;
	}
	
	/**
	 * @param ex		excepcion
	 * @param request	peticion
	 * @return stack de la excepcion
	 */
	private static String getStackTrace(final Exception ex, final WebRequest request) {
		return isIncludeStacktrace(request) ? ExceptionUtils.getStackTrace(ex) : null;
	}
	
	/**
	 * @param request		peticion
	 * @return si se debe incluir el stack de la excepcion
	 */
	private static boolean isIncludeStacktrace(final WebRequest request) {
		String include = ConfiguracionUtil.getAsString(Propiedad.ERROR_INCLUDE_STACKTRACE, "never");
		
		return
				// Si se incluye siempre
				StringUtils.equalsAnyIgnoreCase(include, ErrorProperties.IncludeAttribute.ALWAYS.name()) 
				// Si se ha recibido el parametro trace
				|| (StringUtils.equalsIgnoreCase(include, "on-param") && isIncludeTraceParam(request));
	}
	
	/**
	 * @param request		peticion
	 * @return si se ha recibido el parametro <code>trace</code> con valor <code>true</code>
	 */
	private static boolean isIncludeTraceParam(final WebRequest request) {
		String[] values = request.getParameterValues("trace");
		return Objects.nonNull(values)
				&& values.length > 0
				&& StringUtils.equalsIgnoreCase(values[0], Boolean.TRUE.toString());
	}

	/**
	 * @param ApiError		apiError
	 * @return el objeto ApiError convertido a ErrorLogDTO
	 */	
	private ErrorLogDTO convertirApiErrorAErrorLogDTO(ApiError apiError) {
		
		ErrorLogDTO errorDTO = new ErrorLogDTO();
		
		errorDTO.setId(apiError.getErrorId());
		errorDTO.setFechaHora(apiError.getTimestamp());
		errorDTO.setUsuario(apiError.getUsuario() == null ? null : apiError.getUsuario().getId());
		errorDTO.setStatus(apiError.getStatus());
		errorDTO.setDescripcion(apiError.getError());
		errorDTO.setCodigo(apiError.getCode());
		errorDTO.setMensaje(apiError.getMessage());
		errorDTO.setRuta(apiError.getPath());
		errorDTO.setPila(apiError.getTrace());
		
		return errorDTO;
	}
	
	/**
	 * @param ApiValidationError		apiValidationError
	 * @param String					id
	 * @return el objeto ApiValidationError convertido a ErrorValidacionDTO
	 */	
	private ErrorValidacionDTO convertirApiValidationErrorAErrorValidacionDTO(ApiValidationError apiValidationError, String id) {
		
		ErrorValidacionDTO errorValidacionDTO = new ErrorValidacionDTO();
		
		errorValidacionDTO.setId(apiValidationError.getId());
		errorValidacionDTO.setErrorId(id);
		errorValidacionDTO.setFechaHora(apiValidationError.getTimestamp());
		errorValidacionDTO.setUsuario(apiValidationError.getUsuario() == null ? null : apiValidationError.getUsuario().getId());
		errorValidacionDTO.setCampo(apiValidationError.getField());
		errorValidacionDTO.setMensaje(apiValidationError.getMessage());
		errorValidacionDTO.setCodigo(apiValidationError.getCode());
		
		return errorValidacionDTO;
	}
	
}
