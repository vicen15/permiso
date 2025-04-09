package es.sgad.trama.permiso.service.error;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sgad.trama.permiso.domain.error.ErrorValidacionDTO;
import es.sgad.trama.permiso.mapper.error.ErrorValidacionMapper;
import es.sgad.trama.permiso.persistance.entity.error.ErrorLogEntity;
import es.sgad.trama.permiso.persistance.entity.error.ErrorValidacionEntity;
import es.sgad.trama.permiso.persistance.error.ErrorLogRepository;
import es.sgad.trama.permiso.persistance.error.ErrorValidacionRepository;

@Service
public class ErrorValidacionServiceImpl implements ErrorValidacionService{
	
    @Autowired
    private ErrorValidacionRepository errorValidacionRepository;
    
    @Autowired
    private ErrorLogRepository errorLogRepository;
    
    @Autowired
    private ErrorValidacionMapper errorValidacionMapper;

	@Override
	public ErrorValidacionDTO saveErrorValidacion(ErrorValidacionDTO errorValidacionDTO) {
		// Convertimos el DTO a entidad
		ErrorValidacionEntity errorValidacionEntity = this.errorValidacionMapper.toEntity(errorValidacionDTO);
		
		ErrorLogEntity errorLogEntity = null;
		
		if(errorValidacionDTO.getErrorId() != null)
			errorLogEntity = this.errorLogRepository.findById(errorValidacionDTO.getErrorId()).orElse(null);
		
		//Guardamos el ErrorLogEntity primero
		if (errorLogEntity != null) {
			errorLogRepository.save(errorLogEntity);
			errorValidacionEntity.setErrorLog(errorLogEntity);
		}
		
		// Guardar ErrorLogEntity
		errorValidacionEntity = errorValidacionRepository.save(errorValidacionEntity);

		// Convertimos la entidad guardada de nuevo a DTO y lo devolvemos
		return this.errorValidacionMapper.toDomain(errorValidacionEntity);
	}
    
}