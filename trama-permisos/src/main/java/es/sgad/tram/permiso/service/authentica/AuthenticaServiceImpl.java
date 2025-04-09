package es.sgad.trama.permiso.service.authentica;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;

import es.sgad.authentica.webclient.client.AuthenticaWebClient;
import es.sgad.authentica.webclient.exception.AuthenticaClientException;
import es.sgad.authentica.webclient.model.UserTokenInfo;
import es.sgad.trama.common.configuracion.ConfiguracionUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * Implementacion de {@link AuthenticaService}.
 */
@Slf4j
@Service
public class AuthenticaServiceImpl implements AuthenticaService {
	
	/** Cliente */
	private final AuthenticaWebClient client;
	
	private final Environment environment;
	
	/**
	 * Constructor.
	 * 
	 * @throws Exception
	 */
	public AuthenticaServiceImpl(Environment environment) throws Exception {
		
		this.environment = environment;
		
		ConfiguracionUtil.createInstance(environment);  
		
		this.client = new AuthenticaWebClient(ConfiguracionUtil.getProperties());
	}

	@Override
	public boolean verify(DecodedJWT jwt) {
		
		// Validar que el token no sea null o vacío
		if (jwt == null ) {
			throw new IllegalArgumentException("El token es null y no puede ser verificado.");
		} 
		
		try {
			// Se valida el token
			this.client.verify(jwt);
			return true;
			
		} catch (final AuthenticaClientException e) {
			log.error("El token no es valido", e);
		}
		
		return false;
	}
	
	@Override
	public UserTokenInfo getUserInfo(String token) {
		
		// Validar que el token no sea null o vacío
		if (token == null || token.trim().isEmpty()) {
			throw new IllegalArgumentException("El token no puede ser null o vacío");
		} 
		
		try {
			// Se obtiene la informacion
			return this.client.getUserInfo(token);
			
		} catch (final AuthenticaClientException e) {
			log.error("No se ha podido obtener la informacion del usuario", e);
		}
		 
		return null;
	}

	
}
