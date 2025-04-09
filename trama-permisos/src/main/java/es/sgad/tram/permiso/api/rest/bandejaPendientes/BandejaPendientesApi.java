package es.sgad.trama.permiso.api.rest.bandejaPendientes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaPendientes.PantallaBandejaPendientesDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.SolicitudPermisoPendienteDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.criteria.FiltroBandejaPendientesDTOSearchCriteria;
import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API REST con las operaciones sobre la Bandeja de permisos pendientes.
 */
@Tag(name = "BandejaPermisosPendientes", description = "Bandeja de los permisos pendientes de un usuario")
@RequestMapping(path = BandejaPendientesApi.PATH)
public interface BandejaPendientesApi {


	/** Endpoint */
	static final String PATH = "/api/permiso/bandejaPermisosPendientes";

	@Operation(summary= "Obtener los Permisos pendientes de un usuario", description ="Obtener los Permisos pendientes de un usuario ya sea como solicitante, validador o autorizador")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_BANDEJA_PERMISOS_PENDIENTES,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PantallaBandejaPendientesDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_BANDEJA_PERMISOS_PENDIENTES,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@GetMapping()
	public ResponseEntity<PantallaBandejaPendientesDTO> getPantallaBandejaPendientes(@RequestParam String idUsuario);

	@Operation(summary= "Buscar los Permisos pendientes de un usuario", description ="Buscar los Permisos pendientes de un usuario ya sea como solicitante, validador o autorizador")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_BANDEJA_PERMISOS_PENDIENTES,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SolicitudPermisoPendienteDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_BANDEJA_PERMISOS_PENDIENTES,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping("/search")
	public SearchPageResponse<SolicitudPermisoPendienteDTO> searchSolicitudPermiso(
			@RequestBody FiltroBandejaPendientesDTOSearchCriteria criteria);
	
}