package es.sgad.trama.permiso.service.usuario;

import java.util.UUID;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;

public interface UsuarioService {

	/**
	 * Recupera un usuario dado un id
	 * @param id
	 * @return
	 */
	UsuarioDTO getUsuarioById(String id);
	
	/**
	 * Obtiene el usuario con el DocIdent.
	 * 
	 * @param dni
	 * @return
	 */
	UsuarioDTO getByDocIdent(final String docIdent);

}