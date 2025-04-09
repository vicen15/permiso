package es.sgad.trama.permiso.api.rest.solicitudPermiso;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.sgad.trama.common.exception.FirmaException;
import es.sgad.trama.common.exception.ValidationException;
import es.sgad.trama.permiso.api.rest.solicitudPermiso.validator.SolicitudPermisoDTOValidator;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.solicitudPermiso.NuevaSolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.criteria.SolicitudPermisoSearchCriteria;
import es.sgad.trama.permiso.mapper.solicitudPermiso.SolicitudPermisoMapper;
import es.sgad.trama.permiso.service.rules.engine.PermisoRulesEngine;
import es.sgad.trama.permiso.service.solicitudPermiso.SolicitudPermisoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SolicitudPermisoController implements SolicitudPermisoApi {
	
	private final SolicitudPermisoService solicitudPermisoService; 
	
	private final SolicitudPermisoMapper solicitudPermisoMapper;

	@Autowired
	private SolicitudPermisoDTOValidator solicitudPermisoDTOValidator;
	
	@Autowired
	private PermisoRulesEngine permisoRulesEngine;
	
	@Override
	public SearchPageResponse<SolicitudPermisoDTO> searchSolicitudPermiso(
			SolicitudPermisoSearchCriteria criteria) {
		return solicitudPermisoService.searchSolicitudPermiso(solicitudPermisoMapper.toFilter(criteria), criteria.getPage(), criteria.getSize());
	}

	
	@Override
	public ResponseEntity<SolicitudPermisoDTO> nuevaSolicitudPermiso(
			@Nullable MultipartFile[] archivos,
			@Valid NuevaSolicitudPermisoDTO nuevaSolicitudPermisoDTO,
			BindingResult bindingResult,
			Principal usuarioLogeado
			) throws FirmaException {

			
		//TODO HHH El usuario genera la firma de solicitud de permiso en el cliente???
		//TODO HHH Si la firma proviene del cliente y esta incluida en nueva solicitud permiso DTO, solo es necesario validar que la firma es correcta???
		//Realizamos validacion
		try {
			solicitudPermisoDTOValidator.validate(nuevaSolicitudPermisoDTO, bindingResult, archivos, usuarioLogeado);
		}catch(FirmaException e) {
			throw new FirmaException("Error al validar con el servicio @firma",e.getCause());
		}

		//Comprobamos errores de validacion en los datos de entrada
		if(bindingResult.hasErrors()) {
			throw new ValidationException(bindingResult) ;
		}		
		
		permisoRulesEngine.executeNuevaSolicitudPermisoRules(nuevaSolicitudPermisoDTO, bindingResult);
		
		//Comprobamos errores de validacion de negocio
		if(bindingResult.hasErrors()) {
			throw new ValidationException(bindingResult) ;
		}
		
		List<MultipartFile> listaArchivos = (archivos == null) ? null : Arrays.asList(archivos);
		
		SolicitudPermisoDTO solicitudPermisoDTO = solicitudPermisoService.saveNuevaSolicitudPermiso(nuevaSolicitudPermisoDTO, listaArchivos, bindingResult);

		
		return  ResponseEntity.ok(solicitudPermisoDTO);
	}

}
