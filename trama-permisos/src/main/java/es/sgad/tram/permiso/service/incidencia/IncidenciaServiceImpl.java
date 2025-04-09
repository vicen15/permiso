package es.sgad.trama.permiso.service.incidencia;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sgad.trama.common.exception.InternalErrorException;
import es.sgad.trama.permiso.persistance.incidencia.IncidenciaRepository;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

	@Autowired
	private IncidenciaRepository incidenciaRepository;

	
	@Override
	public Boolean existenIncidenciasQueSeSolapenConNuevoPermiso(Boolean nuevo_permiso_es_diario, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String idUsuario) {
		if(fechaInicio == null ) {
	        throw new InternalErrorException(
	                new IllegalArgumentException("Parametro fechaInicio no puede ser nulo en la funcion."));
		}
	
		if(idUsuario == null || StringUtils.isBlank(idUsuario)) {
			 throw new InternalErrorException(
	                    new IllegalArgumentException("Parametro idUsuario no puede ser nulo o blanco."));
		}
		return incidenciaRepository.queryContarIncidenciasQueSeSolapenConNuevoPermiso(nuevo_permiso_es_diario ? 1 : 0, fechaInicio, fechaFin, horaInicio, horaFin, idUsuario) > 0 ;
	}


}
