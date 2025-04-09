package es.sgad.trama.permiso.persistance.ambito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;

@Repository
public interface AmbitoRepository extends JpaRepository<AmbitoEntity, String>, JpaSpecificationExecutor<AmbitoEntity>   {

}