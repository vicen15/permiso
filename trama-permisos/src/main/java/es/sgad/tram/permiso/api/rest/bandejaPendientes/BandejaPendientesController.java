package es.sgad.trama.permiso.api.rest.bandejaPendientes;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import es.sgad.trama.common.exception.ValidationException;
import es.sgad.trama.common.log.LogExecutionTime;
import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.PantallaBandejaPendientesDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.SolicitudPermisoPendienteDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.criteria.FiltroBandejaPendientesDTOSearchCriteria;
import es.sgad.trama.permiso.mapper.bandejaPendientes.SolicitudPermisoPendienteMapper;
import es.sgad.trama.permiso.service.bandejaPendientes.BandejaPendientesService;
import es.sgad.trama.permiso.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController("BandejaPermisosPendientesController")
@RequiredArgsConstructor
public class BandejaPendientesController implements BandejaPendientesApi {
	
	private final BandejaPendientesService bandejaPendientesService; 
	
	private final SolicitudPermisoPendienteMapper solicitudPermisoPendienteMapper;
	
	private final UsuarioService usuarioService;
	
	@LogExecutionTime
	@Override
	public ResponseEntity<PantallaBandejaPendientesDTO> getPantallaBandejaPendientes(String idUsuario) {
	
		String idAmbito = getUserIdAmbito(idUsuario);
		
		return ResponseEntity.ok(bandejaPendientesService.getPantallaBandejaPendientes(idUsuario, idAmbito));
	}

	@LogExecutionTime
	@Override
	public SearchPageResponse<SolicitudPermisoPendienteDTO> searchSolicitudPermiso(
			FiltroBandejaPendientesDTOSearchCriteria criteria) {

			if(StringUtils.isBlank(criteria.getIdAccion()) || 
					!(criteria.getIdAccion().equals(Constantes.VALOR_STRING_TODAS) ||criteria.getIdAccion().equals(Constantes.VALOR_STRING_VALIDAR) || 
							criteria.getIdAccion().equals(Constantes.VALOR_STRING_AUTORIZAR) || 
							criteria.getIdAccion().equals(Constantes.VALOR_STRING_PTES_DE_MI))) {
				// Si hay acción y no es ninguna de las tres opciones posibles de accion, entonces error
				String message;
				
				if(StringUtils.isBlank(criteria.getIdAccion())){
					message = "Acción no indicada.".concat(" Contacte con el responsable de la aplicación");
				}else {
					message = "Acción no valida. valores permitidos para IdAccion: "
							.concat(Constantes.VALOR_STRING_TODAS).concat(", ")
							.concat(Constantes.VALOR_STRING_VALIDAR).concat(", ")
							.concat(Constantes.VALOR_STRING_AUTORIZAR).concat(", ")
							.concat(Constantes.VALOR_STRING_PTES_DE_MI);


				}
				throw new ValidationException(new HashMap<String, String>(), "IdAccion", message, null, message);

			}else if(StringUtils.isBlank(criteria.getIdAmbito())) {
				criteria.setIdAmbito(getUserIdAmbito(criteria.getIdUsuario()));
			}
				
		return bandejaPendientesService.searchBandejaPendientes(solicitudPermisoPendienteMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());

	}

	private String getUserIdAmbito(String idUsuario) {
		
		UsuarioDTO usuario = usuarioService.getUsuarioById(idUsuario);
		
		String idAmbito = null;
		
		if (usuario != null) {
			idAmbito = usuario.getIdAmbito();
			if(StringUtils.isBlank(idAmbito)) {
				// Si no hay ambito asignado al usuario, entonces error
				throw new ValidationException(new HashMap<String, String>(), "Usuario",
						"El Usuario no tiene asignado un Ámbito activo. Contacte con el responsable de la aplicación",
						null, "El Usuario no tiene asignado un Ámbito activo. Contacte con el responsable de la aplicación");
			}	
		}else {
			// Si no hay usuario, entonces error
			throw new ValidationException(new HashMap<String, String>(), "Usuario",
					"Usuario no existente. Contacte con el responsable de la aplicación",
					null, "Usuario no existente. Contacte con el responsable de la aplicación");
		}
		return idAmbito;
	}

}
