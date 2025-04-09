package es.sgad.trama.permiso.service.error;

import es.sgad.trama.permiso.domain.error.ErrorValidacionDTO;


public interface ErrorValidacionService {

	/**
	 * Almacenamos los cambios para un error
	 * 
	 * @param errorValidacionDTO
	 * @return
	 */
	ErrorValidacionDTO saveErrorValidacion(ErrorValidacionDTO errorValidacionDTO);
	
}