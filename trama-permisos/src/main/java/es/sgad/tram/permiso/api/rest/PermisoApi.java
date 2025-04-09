package es.sgad.trama.permiso.api.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.PermisoDTO;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.criteria.PermisoSearchCriteria;
import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API REST con las operaciones sobre los tipos de permiso.
 */
@Tag(name = "Permiso", description = "Operaciones sobre los datos de los permisos")
@RequestMapping(path = PermisoApi.PATH)
public interface PermisoApi {

	/** Endpoint */
	static final String PATH = "/api/permiso";

	@Operation(summary=  Constantes.PERMISO_GETALL_SUMMARY, description =Constantes.PERMISO_GETALL_DESCRIPTION)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermisoDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@GetMapping
	public ResponseEntity<List<PermisoDTO>> getAllPermiso();

	@Operation(summary= Constantes.PERMISO_GETBYID_SUMMARY  , description = Constantes.PERMISO_GETBYID_DESCRIPTION)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETBYID_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermisoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_PERMISO_NO_VALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@GetMapping("/{id}")
	public ResponseEntity<PermisoDTO> getPermisoById(@PathVariable String id);

	@Operation(summary=  Constantes.PERMISO_CREATE_SUMMARY , description = Constantes.PERMISO_CREATE_DESCRIPTION )
	@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = Constantes.RESPUESTA_201_CREATE_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermisoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping
	public ResponseEntity<PermisoDTO> createPermiso(
			@RequestBody PermisoDTO permisoDTO);

	@DeleteMapping("/{id}")
	@Operation(summary= Constantes.PERMISO_DELETE_SUMMARY   , description = Constantes.PERMISO_DELETE_DESCRIPTION )
	@ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = Constantes.RESPUESTA_204_DELETE_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermisoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_PERMISO_NO_VALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	public ResponseEntity<Void> deletePermiso(@PathVariable String id);

	@Operation(summary=   Constantes.PERMISO_MODIFY_SUMMARY , description = Constantes.PERMISO_MODIFY_DESCRIPTION )
	@ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = Constantes.RESPUESTA_204_MODIFY_PERMISO),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_PERMISO_NO_VALIDO,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PutMapping("/{id}")
	public ResponseEntity<PermisoDTO> modifyPermiso(@PathVariable String id,
			@RequestBody PermisoDTO permisoDTO);

	@Operation(summary= Constantes.PERMISO_SEARCH_SUMMARY , description = Constantes.PERMISO_SEARCH_DESCRIPTION)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constantes.RESPUESTA_200_GETALL_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermisoDTO.class))),
            @ApiResponse(responseCode = "400", description = Constantes.RESPUESTA_400_PARAMETRO_INVALIDO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "404", description = Constantes.RESPUESTA_404_PERMISO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class))),
            @ApiResponse(responseCode = "500", description = Constantes.RESPUESTA_500_INTERNO,
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorLogDTO.class)))
    })
	@PostMapping("/search")
	public SearchPageResponse<PermisoDTO> searchPermiso(
			@RequestBody PermisoSearchCriteria criteria);
}
