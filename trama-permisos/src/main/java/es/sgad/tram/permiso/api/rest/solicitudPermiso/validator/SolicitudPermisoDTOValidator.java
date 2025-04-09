package es.sgad.trama.permiso.api.rest.solicitudPermiso.validator;

import java.security.Principal;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import es.sgad.trama.common.exception.FirmaException;
import es.sgad.trama.common.idioma.Mensaje;
import es.sgad.trama.common.utils.SeguridadUtil;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoValidFileExtension;
import es.sgad.trama.permiso.service.PermisoService;
import es.sgad.trama.permiso.service.tipoPermisoAmbito.TipoPermisoAmbitoService;
import es.sgad.trama.permiso.service.usuario.UsuarioService;


@Component
public class SolicitudPermisoDTOValidator implements Validator{
	
	
	//TODO HHH: Establecer estas variables en properties o bbdd para que sean parametrizables
	final Integer PERMISO_DOCUMENTO_MAXIMOS_CARACTERES_NOMBRE = 100;//????? Preguntar el maximo
	final Integer PERMISO_DETALLE_NUMERO_MAXIMO = 10;// Numero maximo de detalles de una permiso
	final Integer PERMISO_OBSERVACIONES_USUARIO_MAX_CARACTERES = 4000;
	final Integer PERMISO_DETALLE_OBSERVACIONES_MAX_CARACTERES = 4000;
	
	@Autowired
	TipoPermisoAmbitoService tipoPermisoAmbitoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PermisoService permisoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SolicitudPermisoDTO.class.isAssignableFrom(clazz);
	}


	public void validate(Object target, Errors errors, MultipartFile[] archivos, Principal usuarioLogeado) throws FirmaException{

		NuevaSolicitudPermisoDTO solicitud = (NuevaSolicitudPermisoDTO) target;
		TipoPermisoAmbitoDTO tipoPermisoAmbitoDTO = null;
		
		if(solicitud.getIdTipoPermisoAmbito() != null) {
			tipoPermisoAmbitoDTO = tipoPermisoAmbitoService.getTipoPermisoAmbitoById(solicitud.getIdTipoPermisoAmbito());		
		}
		
		//Comprobar que el usuario logado corresponde con el solicitante
		String usuarioDocIdent = SeguridadUtil.extraerDocIdentFromRequestRemoteUser(usuarioLogeado.getName());
		
		UsuarioDTO usuario = usuarioService.getByDocIdent(usuarioDocIdent);
		//Comprobar que el usuario se encuentra activo, si el usuario que realiza la peticion, es el mismo que el usuario en sesion
		//significa que el usuario se encuentra dado de alta en el sistema.
		if(!solicitud.getIdSolicitante().equals(usuario.getId())){
			errors.rejectValue("idSolicitante", Mensaje.SOLICITUD_PERMISO_USER_INVALID.getCodigo(), "default El usuario logeado no corresponde con el usuario que realizo la solicitud.");
		}
		
		//PERMISO
		if(solicitud.getIdTipoPermisoAmbito() == null) {
			errors.rejectValue("idTipoPermiso", Mensaje.SOLICITUD_PERMISO_IDTIPOPERMISOAMBITO_REQUIRED.getCodigo(), "default idTipoPermisoAmbito no puede ser nulo");
		}
		
		if(solicitud.getIdTipoPermisoAmbito() != null && StringUtils.isBlank(solicitud.getIdTipoPermisoAmbito())) {
			errors.rejectValue("idTipoPermiso", Mensaje.SOLICITUD_PERMISO_IDTIPOPERMISOAMBITO_NOBLANK.getCodigo(),"default idTipoPermisoAmbito no puede estar en blanco.");
		}
		
		//SOLICITANTE
		if(solicitud.getIdSolicitante() == null) {
			errors.rejectValue("idSolicitante",  Mensaje.SOLICITUD_PERMISO_IDSOLICITANTE_REQUIRED.getCodigo(), "default idSolicitante no puede ser nulo");
		}
		
		if(solicitud.getIdSolicitante() != null && StringUtils.isBlank(solicitud.getIdSolicitante())) {
			errors.rejectValue("idSolicitante",  Mensaje.SOLICITUD_PERMISO_IDSOLICITANTE_NOBLANK.getCodigo(), "default idSolicitante no puede estar en blanco.");
		}
		
		//OBSERVACIONES USUARIO 
		if(solicitud.getObservacionesParaUsuario().length() > PERMISO_OBSERVACIONES_USUARIO_MAX_CARACTERES) {
			errors.rejectValue("observacionesParaUsuario",  Mensaje.SOLICITUD_PERMISO_OBSERVACIONES_PARA_USUARIO_MAX_LENGTH.getCodigo(), "default Maximo caracteres permitido superado.");
		}
		
		//COMPROBAR EXISTEN DETALLES
		if(solicitud.getListaSolicitudPermisoDetalle() != null) {
			if(solicitud.getListaSolicitudPermisoDetalle().size() == 0) {
				errors.rejectValue("listaSolicitudPermisoDetalle",  Mensaje.SOLICITUD_PERMISO_LISTADETALLE_EMPTY.getCodigo(), "default lista detalles no puede estar vacio");
			}
		}
		
		
		//COMPROBAR DETALLES COINCIDEN CON EL TIPO DE PERMISO
		//LOS DETALLES TIENEN FECHA U HORAS O AMBAS DEPENDIENDO DEL TIPO DE PERMISO JUNTO CON OBSERVACIONES
		//COMPROBAR FECHAS DETALLE SON CORRECTAS
		if(solicitud.getIdTipoPermisoAmbito() != null) {
			validarTipoDuracion(solicitud, errors, tipoPermisoAmbitoDTO);
		}
		if(tipoPermisoAmbitoDTO != null) {
			//COMPROBAR PERMISO DEBE TENER ADJUNTO
			if(tipoPermisoAmbitoDTO.getDebeTenerAdjunto() && (archivos ==null)) {
				errors.rejectValue("listaSolicitudPermisoDocumento",  Mensaje.SOLICITUD_PERMISO_LISTADOCUMENTO_EMPTY.getCodigo(), "default lista documentos no puede estar vacio");
			}
		}
		
		//VALIDAR FIRMA SOLICITANTE
		//TODO HHH Continuar comprobando por que se produce un error 411 al conectar con afirma.
//		try {
//			
//			if(!firmaService.validarFirma(solicitud.getFirmaCriptoSolicitante(), usuario.getDocIdent())) {
//				errors.rejectValue("idSolicitante",  "", "default La firma enviada no es valida.");
//			}
//		
//		}catch(AFirma5Exception ex) {
//			throw new FirmaException("Error al validar la firma en @firma.",ex.getCause());
//		}
		
		//VALIDAR QUE LOS DETALLES SOLCITADOS NO EXISTEN O NO SE SOLAPAN CON LOS DE LA TABLA PERMISO
//		if(validarSiExistenPermisosConMismaFecha(solicitud)) {
//			errors.rejectValue("listaSolicitudPermisoDetalle",  Mensaje.SOLICITUD_PERMISO_DETALLE_EXISTS.getCodigo(), "default No se pueden solicitar fechas previamente solicitadas");
//		}
		
		
		//VALIDAR ARCHIVOS (NOMBRE FICHERO) (EXTENSION)
		if(archivos != null) {
			
			for(MultipartFile archivo : archivos) {
				
				if(!SolicitudPermisoDocumentoValidFileExtension.isValid(archivo.getOriginalFilename())) {
					//Extensiones permitidas
					errors.rejectValue("listaSolicitudPermisoDocumento",  Mensaje.SOLICITUD_PERMISO_DOCUMENTO_VALIDEXT.getCodigo(), "default El documento debe tener una extension valida");
				}
				if(archivo.getOriginalFilename().length() > PERMISO_DOCUMENTO_MAXIMOS_CARACTERES_NOMBRE) {
					//Maximo caracteres en el nombre del fichero
					errors.rejectValue("listaSolicitudPermisoDocumento",  Mensaje.SOLICITUD_PERMISO_DOCUMENTO_MAXFILENAME.getCodigo(), "default Numero de caracteres del nombre del archivo superado.");
				}
			}
		}
		
	}
	
	/**
	 * Segun el tipo de duracion realizamos las validaciones sobre los campos fecha y campos hora
	 * @param solicitud SolicitudPermisoDTO
	 * @param errors Errores
	 * @param permisoDTO PermisoDTO
	 */
	void validarTipoDuracion(NuevaSolicitudPermisoDTO solicitud, Errors errors, TipoPermisoAmbitoDTO permisoDTO) {
		Integer idx = 0;
		for(SolicitudPermisoDetalleDTO d : solicitud.getListaSolicitudPermisoDetalle()) {
			//Validar horas
			validarDetalle(d,errors,idx);
			idx++;
		}
	}
	/**
	 * Consulta en base de datos si existen permisos en las cuales hora inicio u hora fin se solapan con las fechas solicitas
	 * @param dto Solicitud Realizada
	 * @return true si encuentra permisos con misma fecha, false si no encuentra permisos relacionadas
	 */
//	Boolean validarSiExistenPermisosConMismaFecha(NuevaSolicitudPermisoDTO dto) {
//		for(SolicitudPermisoDetalleDTO detalle : dto.getListaSolicitudPermisoDetalle()) {
//			LocalDateTime fechaHoraInicio = LocalDateTime.of(detalle.getFecha(),detalle.getHoraInicio());
//			LocalDateTime fechaHoraFin = LocalDateTime.of(detalle.getFecha(),detalle.getHoraFin());
//			if(permisoService.existenPermisosEntreFechasPorUsuario(fechaHoraInicio, fechaHoraFin, dto.getIdSolicitante())) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	/**
	 * Realiza las validaciones de los campos de tipo hora SolicitudPermisoDetalleDTO
	 * @param detalle SolicitudPermisoDTO
	 * @param errors Errores
	 * @param idx Indice para recorrer el array de detalles
	 */
	void validarDetalle(SolicitudPermisoDetalleDTO detalle, Errors errors, Integer idx) {
		
		String nestedObjectPath = "listaSolicitudPermisoDetalle["+idx+"].";

		//Comprobar que la fecha no es nula en el detalle
		if(detalle.getFechaInicio() == null) {

			errors.rejectValue(nestedObjectPath + "fecha",  Mensaje.SOLICITUD_PERMISO_DETALLE_FECHA_EMPTY.getCodigo(), "default Fecha Desde no puede ser nulo.");
		}

		//Si la solicitud es por fechas no debe de tener horas
		if(detalle.getFechaFin() != null) {
			if(detalle.getHoraInicio() != null) {
				errors.rejectValue(nestedObjectPath + "horaInicio",  Mensaje.SOLICITUD_PERMISO_DETALLE_HORAINICIO_EMPTY.getCodigo(), "default Hora desde no puede tener valor en solicitudes de permisos por fechas.");
			}
			if(detalle.getHoraFin() != null) {
				errors.rejectValue(nestedObjectPath + "horaFin",  Mensaje.SOLICITUD_PERMISO_DETALLE_HORAINICIO_EMPTY.getCodigo(), "default Hora hasta no puede tener valor en solicitudes de permisos por fechas.");
			}
			
			if(detalle.getFechaFin().isBefore(detalle.getFechaInicio())) {
				errors.rejectValue(nestedObjectPath + "fecha",  Mensaje.SOLICITUD_PERMISO_DETALLE_FECHA_NOBEFORE.getCodigo(), "default Fecha hasta no puede ser anterior a la fecha desde.");
			}
			
		//Si es por horas tiene que tener fecha Desde, no tener fecha Hasta y tener hora Desde y Hasta	
		}else{ 
			if(detalle.getHoraInicio() == null) {
				errors.rejectValue(nestedObjectPath + "horaInicio",  Mensaje.SOLICITUD_PERMISO_DETALLE_HORAINICIO_EMPTY.getCodigo(), "default Hora desde no puede tener valor en solicitudes de permisos por horas.");
			}
			if(detalle.getHoraFin() == null) {
				errors.rejectValue(nestedObjectPath + "horaInicio",  Mensaje.SOLICITUD_PERMISO_DETALLE_HORAFIN_EMPTY.getCodigo(), "default Hora desde no puede tener valor en solicitudes de permisos por horas.");
			}
			
			if(detalle.getHoraFin() != null && detalle.getHoraInicio() != null) {
				if(!validaHoraInicioFinCoherente(detalle.getHoraInicio(), detalle.getHoraFin())) {
					errors.rejectValue(nestedObjectPath + "horaInicio",  Mensaje.SOLICITUD_PERMISO_DETALLE_HORAINICIOFIN_LOGIC.getCodigo(), "default Hora inicio no puede ser posterior a hora fin.");
				}
			}
		}
		
		//Comprobar observaciones
		if(detalle.getObservaciones().length() > PERMISO_DETALLE_OBSERVACIONES_MAX_CARACTERES) {

			errors.rejectValue(nestedObjectPath + "observaciones",  Mensaje.SOLICITUD_PERMISO_DETALLE_OBSERVACIONES_MAX_LENGTH.getCodigo(), "defaultMaximo caracteres permitido superado.");
		}

	}
	
	/**
	 * Valida las horas (LocalTime) recibidas como parametros comprobando que las horas y minutos de inicio son inferiores a fin.
	 * @param inicio fecha de inicio
	 * @param fin fecha de fin
	 * @return Boolean true si las fechas son coherentes, false si no son coherentes
	 */
	Boolean validaHoraInicioFinCoherente(LocalTime inicio, LocalTime fin) {
		
		Boolean valido = true;

		if(inicio.isAfter(fin) || inicio.equals(fin)) {
			valido = false;
		}
		
		
		return valido;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	
}
