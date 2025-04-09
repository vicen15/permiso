package es.sgad.trama.permiso.api.rest.solicitudPermiso;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import es.sgad.trama.common.exception.FirmaException;
import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.criteria.SolicitudPermisoSearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API REST con las operaciones sobre Solicitud PERMISO.
 */
@Tag(name = "SolicitudPermiso", description = "Operaciones sobre los datos de solicitud permiso")
@RequestMapping(path = SolicitudPermisoApi.PATH)
public interface SolicitudPermisoApi{

	static final String API_PATH = "/api";
	/** Endpoint */
	static final String PATH = API_PATH + "/solicitudPermiso";

	@Operation(summary= "Buscar los permisos enviados de un usuario", description ="Buscar los permisos enviados de un usuario ya sea como solicitante, validador o autorizador")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SolicitudPermisoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_BANDEJA_PERMISOS_ENVIADOS,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping("/search")
	public SearchPageResponse<SolicitudPermisoDTO> searchSolicitudPermiso(
			@RequestBody SolicitudPermisoSearchCriteria criteria);
	
	@Operation(summary= "Alta de Nueva Solicitud de Incidencia", description ="Realiza el alta de una nueva solicitud de incidencia recibiendo 2 partes. Parte archivos que contiene los archivos adjuntos a la solicitud de incidencia. Parte nuevaSolicitudIncidenciaDTO que recibe un objeto NuevaSolicitudIncidenciaDTO  con los datos de una nueva solicitud de incidencia.")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = Constantes.RESPUESTA_201_ALTA_SOLICITUD_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SolicitudPermisoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping(
			value = "/nuevaSolicitud"
	)
	public ResponseEntity<SolicitudPermisoDTO> nuevaSolicitudPermiso(
			@RequestPart("archivos") MultipartFile[] archivos,
			@RequestPart("nuevaSolicitudPermisoDTO") @Valid NuevaSolicitudPermisoDTO solicitudPermisoDTO,
			BindingResult bindingResult,
			Principal usuarioLogeado
			) throws FirmaException;

}
