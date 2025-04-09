package es.sgad.trama.permiso.api.rest.config.security.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import es.sgad.trama.common.configuracion.ConfiguracionUtil;
import es.sgad.trama.common.configuracion.Propiedad;
import es.sgad.trama.common.idioma.IdiomaUtil;
import es.sgad.trama.common.idioma.MensajeError;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.service.authentica.AuthenticaService;
import es.sgad.trama.permiso.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio para la verificacion del token JWT.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
	
	/** Servicio de Authentica */
	private final AuthenticaService authenticaService;
	
	/** Servicio de usuarios */
	private final UsuarioService usuarioService;


	/**
	 * Comprueba si el token es valido.
	 *
	 * @param token		token JWT
	 * @return informacion del usuario
	 * @throws BadCredentialsException
	 */
	public UsuarioDTO verify(final String token) throws BadCredentialsException {
		log.info("Verificando token...");
		log.debug("Token: " + token);

		// Se comprueba que el token no este vacio
		if (StringUtils.isBlank(token))
			throw new BadCredentialsException("Error al validar el token de seguridad. El token esta vacio");

		// Se decodifica el token
		DecodedJWT jwt = JWT.decode(token);
		if (Objects.isNull(jwt))
			throw new BadCredentialsException("Error al decodificar el token de seguridad");

		// Se valida el token
		this.validate(jwt);

		// Se verifica el token
		this.verify(jwt);
		
		// Se obtiene la info del usuario
//		UsuarioDTO usuario = this.usuarioService.getUsuarioByUSername(jwt.getClaim("username").asString());
		UsuarioDTO usuario = this.usuarioService.getByDocIdent(jwt.getClaim("username").asString());
		if (Objects.isNull(usuario))
			throw new UsernameNotFoundException("El usuario no existe");
		
		return usuario;
	}

	/**
	 * Obtiene el identificador de la aplicacion.
	 *
	 * @param token		token JWT
	 * @return identificador
	 * @throws BadCredentialsException
	 */
	public String getIdApp(final String token) throws BadCredentialsException {
		log.info("Obteniendo identificador...");
		log.debug("Token: " + token);
		
		// Se comprueba que el token no este vacio
		if (StringUtils.isBlank(token))
			throw new BadCredentialsException("Error al validar el token de seguridad. El token esta vacio");
		
		// Se decodifica el token
		DecodedJWT jwt = JWT.decode(token);
		
		// Se obtiene el identificador
		byte[] payload = Base64.getDecoder().decode(jwt.getPayload());
		return new String(payload, StandardCharsets.UTF_8);
	}

	/**
	 * Valida el token.
	 *
	 * @param jwt	token JWT
	 * @throws BadCredentialsException
	 */
	private void validate(final DecodedJWT jwt) throws BadCredentialsException {
		// Se valida el formato
		if (Objects.isNull(jwt.getHeader()))
			throw this.getException(MensajeError.JWT_TIPO_FORMATO, MensajeError.JWT_CAUSA_NO_HEADER);
		
		if (Objects.isNull(jwt.getSignature()))
			throw this.getException(MensajeError.JWT_TIPO_FORMATO, MensajeError.JWT_CAUSA_NO_SIGNATURE);
		
		if (Objects.isNull(jwt.getPayload()))
			throw this.getException(MensajeError.JWT_TIPO_FORMATO, MensajeError.JWT_CAUSA_NO_PAYLOAD);

		// Se comprueba si ha caducado
		Date expiresAt = jwt.getExpiresAt();
		if (Objects.isNull(expiresAt))
			throw this.getException(MensajeError.JWT_TIPO_VERIFICACION, MensajeError.JWT_CAUSA_NO_EXPIRES_DATE);

		if (expiresAt.before(new Date()))
			throw this.getException(MensajeError.JWT_TIPO_VERIFICACION, MensajeError.JWT_CAUSA_OFF_DATE);

		// Se comprueba el issuer
		String issuer = jwt.getIssuer();
		if (StringUtils.isBlank(issuer))
			throw this.getException(MensajeError.JWT_TIPO_VALIDACION, MensajeError.JWT_CAUSA_NO_ISSUER);
			
		if (!StringUtils.equals(issuer, ConfiguracionUtil.getAsString(Propiedad.EXT_SERVICE_AUTHENTICA_JWT_VALIDADOR_URL)))
			throw this.getException(MensajeError.JWT_TIPO_VALIDACION, MensajeError.JWT_CAUSA_WRONG_ISSUER);
		
		// Se comprueba el username
//		TODO: aqui tambien habra que cambiarlo!!!!
		String username = jwt.getClaim("username").asString();
		if (StringUtils.isBlank(username))
			throw this.getException(MensajeError.JWT_TIPO_VALIDACION, MensajeError.JWT_CAUSA_NO_USERNAME);
	}

	/**
	 * Verifica que el token es valido.
	 *
	 * @param jwt	token jwt
	 * @throws BadCredentialsException
	 */
	private void verify(final DecodedJWT jwt) throws BadCredentialsException {
		// Se verifica
		if (!this.authenticaService.verify(jwt))
			throw this.getException(MensajeError.JWT_TIPO_VERIFICACION, null);
	}
	
	/**
	 * Genera una excepcion con el error indicado.
	 * 
	 * @param tipo		tipo de error
	 * @param causa		causa del error
	 * @return excepcion
	 */
	private BadCredentialsException getException(final MensajeError tipo, @Nullable final MensajeError causa) {
		String message = new StringBuilder()
				.append(IdiomaUtil.getMensaje(tipo.getCodigo()))
				.append(Objects.isNull(causa) ? "" : IdiomaUtil.getMensaje(causa.getCodigo()))
				.toString();
		
		log.error(message);
		return new BadCredentialsException(message);
	}

}
