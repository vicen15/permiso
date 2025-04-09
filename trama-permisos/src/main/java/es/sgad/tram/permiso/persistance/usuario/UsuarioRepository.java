package es.sgad.trama.permiso.persistance.usuario;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> , JpaSpecificationExecutor<UsuarioEntity>   {
	
	/**
	 * Obtiene el usuario con el dni.
	 * 
	 * @param docIdent
	 * @return
	 */
	Optional<UsuarioEntity> findByDocIdentIgnoreCase(final String docIdent);

	/**
	 * TODO: esta no esta buscando por nada por que no existe username en la nueva base de datos
	 * funcion para extraer un usuario en base a su username
	 * 
	 * @param userName
	 * @return
	 */

}