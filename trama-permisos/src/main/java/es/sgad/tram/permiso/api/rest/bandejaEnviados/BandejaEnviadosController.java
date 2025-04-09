package es.sgad.trama.permiso.api.rest.bandejaEnviados;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.sgad.trama.common.exception.ItemNotFoundException;
import es.sgad.trama.common.exception.ValidationException;
import es.sgad.trama.common.log.LogExecutionTime;
import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaEnviados.PantallaBandejaEnviadosDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoEnviadoDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.criteria.FiltroBandejaEnviadosDTOSearchCriteria;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.mapper.bandejaEnviados.SolicitudPermisoEnviadoMapper;
import es.sgad.trama.permiso.service.bandejaEnviados.BandejaEnviadosService;
import es.sgad.trama.permiso.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BandejaEnviadosController implements BandejaEnviadosApi {
	
	private final BandejaEnviadosService bandejaEnviadosService; 
	
	private final SolicitudPermisoEnviadoMapper solicitudPermisoEnviadoMapper;
	
	private final UsuarioService usuarioService;
	
	// Carga de la bandeja de permisos enviados
	@LogExecutionTime
	@Override
	public ResponseEntity<PantallaBandejaEnviadosDTO> getPantallaBandejaEnviados(String idUsuario) {
		
		UsuarioDTO usuario = usuarioService.getUsuarioById(idUsuario);
		String idAmbito = null;
		
		// Comprueba que viene dato en el usuario, que existe y va a buscar el ambito que tiene.
		if (!StringUtils.isBlank(usuario.getId())) {
			idAmbito = usuarioService.getUsuarioById(idUsuario).getIdAmbito();
		}
		
		if (StringUtils.isBlank(usuario.getId()) || StringUtils.isBlank(idAmbito)) {
			String message = "";
			String campo = "";
			
			// Si no hay usuario, entonces error
			if (StringUtils.isBlank(usuario.getId())) {
				message = "Usuario no existente.".concat(" Contacte con el responsable de la aplicación.");
				campo = "Usuario";
				
			// Si no hay ambito asignado al usuario, entonces error
			} else if(StringUtils.isBlank(idAmbito)) {
				message = "El Usuario no tiene asignado un Ámbito activo.".concat(" Contacte con el responsable de la aplicación.");	
				campo = "Ambito";
			}
			
			throw new ItemNotFoundException(campo, message);
			
		// Si no, presenta la pantalla de la bandeja de permisos enviados
		}else {
			return ResponseEntity.ok(bandejaEnviadosService.getPantallaBandejaEnviados(idUsuario, idAmbito));
		}
		
	}
	
	// Busqueda de las solicitudes de la bandeja de permisos enviados
	@LogExecutionTime
	@Override
	public SearchPageResponse<SolicitudPermisoEnviadoDTO> searchSolicitudPermiso(
			FiltroBandejaEnviadosDTOSearchCriteria criteria) {
		

		String message;
		String campo;
		
		if(!StringUtils.isBlank(criteria.getIdUsuario()) && StringUtils.isBlank(criteria.getIdAmbito())) {
			criteria.setIdAmbito(usuarioService.getUsuarioById(criteria.getIdUsuario()).getIdAmbito());
		}
		
		// Si no hay estado o si hay estado y no es ninguno de las seis opciones posibles de estado, entonces error
		if(StringUtils.isBlank(criteria.getIdEstado()) ||
				(!StringUtils.isBlank(criteria.getIdEstado()) && 
					!(criteria.getIdEstado().equals(Constantes.VALOR_STRING_TODAS) || criteria.getIdEstado().equals(Constantes.VALOR_STRING_EN_PROCESO) || 
					criteria.getIdEstado().equals(Constantes.VALOR_STRING_ADMITIDAS) || criteria.getIdEstado().equals(Constantes.VALOR_STRING_DENEGADAS) ||
					criteria.getIdEstado().equals(Constantes.VALOR_STRING_CANCELADAS) || criteria.getIdEstado().equals(Constantes.VALOR_STRING_DESISTIDAS)))) {
						
			if(StringUtils.isBlank(criteria.getIdEstado())){
				message = "Estado no indicado.".concat(" Contacte con el responsable de la aplicación.");
			}else {
				message = "Estado no valido. Valores permitidos para Estado: "
						.concat(Constantes.VALOR_STRING_TODAS).concat("(TODAS), ")
						.concat(Constantes.VALOR_STRING_EN_PROCESO).concat("(EN PROCESO),")
						.concat(Constantes.VALOR_STRING_ADMITIDAS).concat("(ADMITIDAS),")
						.concat(Constantes.VALOR_STRING_DENEGADAS).concat("(DENEGADAS),")
						.concat(Constantes.VALOR_STRING_CANCELADAS).concat("(CANCELADAS),")
						.concat(Constantes.VALOR_STRING_DESISTIDAS).concat("(DESISTIDAS)");

			}
			throw new ValidationException(new HashMap<String, String>(), "IdEstado", message, null, message);

		// Si no hay acción o si hay acción y no es ninguna de las tres opciones posibles de accion, entonces error
		} else if(StringUtils.isBlank(criteria.getIdAccion()) ||
				(!StringUtils.isBlank(criteria.getIdAccion()) && 
					!(criteria.getIdAccion().equals(Constantes.VALOR_STRING_CREADAS_POR_MI) || criteria.getIdAccion().equals(Constantes.VALOR_STRING_VALIDADAS_POR_MI) || 
					criteria.getIdAccion().equals(Constantes.VALOR_STRING_AUTORIZADAS_POR_MI)))) {
						
			if(StringUtils.isBlank(criteria.getIdAccion())){
				message = "Acción no indicada.".concat(" Contacte con el responsable de la aplicación.");
			}else {
				message = "Acción no valida. Valores permitidos para IdAccion: "
						.concat(Constantes.VALOR_STRING_CREADAS_POR_MI).concat(" (CREADAS POR MI), ")
						.concat(Constantes.VALOR_STRING_VALIDADAS_POR_MI).concat("(VALIDADAS POR MI), ")
						.concat(Constantes.VALOR_STRING_AUTORIZADAS_POR_MI).concat("(AUTORIZADAS POR MI)");

			}
			throw new ValidationException(new HashMap<String, String>(), "IdAccion", message, null, message);

		// Si no hay usuario, entonces error
		} else if (StringUtils.isBlank(criteria.getIdUsuario())) {
			message = "Usuario no existente.".concat(" Contacte con el responsable de la aplicación.");
			campo = "Usuario";
			throw new ItemNotFoundException(campo, message);
		
		// Si no hay ambito asignado al usuario, entonces error
		} else if (StringUtils.isBlank(criteria.getIdAmbito())) {
			message = "El Usuario no tiene asignado un Ámbito activo.".concat(" Contacte con el responsable de la aplicación.");	
			campo = "Ambito";
			throw new ItemNotFoundException(campo, message);
		
		// Si no, presenta la pantalla de la bandeja de permisos enviados
		} else {
			return bandejaEnviadosService.searchBandejaEnviados(solicitudPermisoEnviadoMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
		}
		
	}
	
	// Busqueda de los solicitantes de las solicitudes de la bandeja de permisos enviados
	@LogExecutionTime
	@Override
	public ResponseEntity<List<SolicitanteComboDTO>> searchSolicitanteCombo(
			@RequestBody FiltroBandejaEnviadosDTOSearchCriteria criteria, String idUsuario) {
		
		
		String message;
		String campo;
		
		if(!StringUtils.isBlank(criteria.getIdUsuario()) && StringUtils.isBlank(criteria.getIdAmbito())) {
			criteria.setIdAmbito(usuarioService.getUsuarioById(criteria.getIdUsuario()).getIdAmbito());
		}
		
		// Si no hay usuario, entonces error
		if (StringUtils.isBlank(criteria.getIdUsuario())) {
			message = "Usuario no existente.".concat(" Contacte con el responsable de la aplicación.");
			campo = "Usuario";
			throw new ItemNotFoundException(campo, message);
		
		// Si no hay ambito asignado al usuario, entonces error
		} else if (StringUtils.isBlank(criteria.getIdAmbito())) {
			message = "El Usuario no tiene asignado un Ámbito activo.".concat(" Contacte con el responsable de la aplicación.");	
			campo = "Ambito";
			throw new ItemNotFoundException(campo, message);
		
		// Si no, presenta la pantalla de la bandeja de permisos enviados
		} else {
			return ResponseEntity.ok(bandejaEnviadosService.getListaSolicitantes(solicitudPermisoEnviadoMapper.toFilter(criteria), criteria.getPage(), criteria.getSize()));
		}
		
	}

}
