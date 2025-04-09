package es.sgad.trama.permiso.service.error;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sgad.trama.permiso.domain.error.ErrorLogDTO;
import es.sgad.trama.permiso.mapper.error.ErrorLogMapper;
import es.sgad.trama.permiso.persistance.entity.error.ErrorLogEntity;
import es.sgad.trama.permiso.persistance.error.ErrorLogRepository;



@Service
public class ErrorLogServiceImpl implements ErrorLogService{
	
    @Autowired
    private ErrorLogRepository errorLogRepository;
    
    @Autowired
    private ErrorLogMapper errorLogMapper;

	@Override
	public ErrorLogDTO saveErrorLog(ErrorLogDTO errorLogDTO) {
		// Convertimos el DTO a entidad
		ErrorLogEntity errorLogEntity = this.errorLogMapper.toEntity(errorLogDTO);
		
		// Guardar ErrorLogEntity
		errorLogEntity = errorLogRepository.save(errorLogEntity);

		// Convertimos la entidad guardada de nuevo a DTO y lo devolvemos
		return this.errorLogMapper.toDomain(errorLogEntity);
	}
    
}