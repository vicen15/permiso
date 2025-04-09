package es.sgad.trama.permiso.api.rest.bandejaEnviados;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaEnviados.PantallaBandejaEnviadosDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoEnviadoDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.criteria.FiltroBandejaEnviadosDTOSearchCriteria;
import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API REST con las operaciones sobre la Bandeja de permisos enviados.
 */
@Tag(name = "BandejaPermisosEnviados", description = "Bandeja de los permisos enviados de un usuario")
@RequestMapping(path = BandejaEnviadosApi.PATH)
public interface BandejaEnviadosApi {

	/** Endpoint */
	static final String PATH = "/api/permiso/bandejaPermisosEnviados";

	// Carga de la bandeja de permisos enviados
	@Operation(summary= "Obtener los permisos enviados de un usuario", description ="Obtener las permisos enviados de un usuario ya sea como solicitante, validador o autorizador")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PantallaBandejaEnviadosDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@GetMapping()
	public ResponseEntity<PantallaBandejaEnviadosDTO> getPantallaBandejaEnviados(@RequestParam String idUsuario);

	// Busqueda de las solicitudes de la bandeja de permisos enviadas
	@Operation(summary= "Buscar los permisos enviados de un usuario", description ="Buscar los permisos enviados de un usuario ya sea como solicitante, validador o autorizador")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SolicitudPermisoEnviadoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping("/search")
	public SearchPageResponse<SolicitudPermisoEnviadoDTO> searchSolicitudPermiso(
			@RequestBody FiltroBandejaEnviadosDTOSearchCriteria criteria);
	
	// Busqueda de los solicitantes de las solicitudes de la bandeja de permisos enviados
	@Operation(summary= "Buscar los solicitantes de las permisos enviados de un usuario", description ="Buscar los solicitantes de los permisos enviados de un usuario ya sea como solicitante, validador o autorizador")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SolicitanteComboDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping("/searchSolicitante")
	public ResponseEntity<List<SolicitanteComboDTO>> searchSolicitanteCombo(
			@RequestBody FiltroBandejaEnviadosDTOSearchCriteria criteria, String idUsuario);
	
}