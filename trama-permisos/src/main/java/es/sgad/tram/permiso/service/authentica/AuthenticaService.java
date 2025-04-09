package es.sgad.trama.permiso.service.authentica;

import com.auth0.jwt.interfaces.DecodedJWT;

import es.sgad.authentica.webclient.model.UserTokenInfo;

/**
 * Servicio de acceso al servicio externo de Authentica.
 */
public interface AuthenticaService {
	
	/**
	 * Verifica el token JWT.
	 * 
	 * @param jwt	token JWT
	 * @return si es valido
	 */
	boolean verify(final DecodedJWT jwt);
	
	/**
	 * Obtiene la informacion del usuario asociado al token.
	 * 
	 * @param token		token JWT
	 * @return datos del usuario
	 */
	UserTokenInfo getUserInfo(final String token);

	
}
