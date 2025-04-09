package es.sgad.trama.permiso.persistance.error;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.persistance.entity.error.ErrorLogEntity;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLogEntity, String> {

	
}