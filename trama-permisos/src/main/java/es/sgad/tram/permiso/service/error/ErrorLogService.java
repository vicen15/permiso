package es.sgad.trama.permiso.service.error;

import es.sgad.trama.permiso.domain.error.ErrorLogDTO;


public interface ErrorLogService {

	/**
	 * Almacenamos los cambios para un error
	 * 
	 * @param errorLogDTO
	 * @return
	 */
	ErrorLogDTO saveErrorLog(ErrorLogDTO errorLogDTO);
	
}