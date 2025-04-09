package es.sgad.trama.permiso.api.rest.config.security.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import es.sgad.trama.common.configuracion.ConfiguracionUtil;
import es.sgad.trama.common.configuracion.Propiedad;
import es.sgad.trama.permiso.api.rest.config.security.util.JwtService;
import es.sgad.trama.permiso.api.rest.config.security.util.SecurityUtil;
import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Filtro encargado de comprobar el token JWT.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	/** Servicio para tokens JET */	
	private final JwtService jwtService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// Si esta activo
		if (ConfiguracionUtil.getAsBoolean(Propiedad.JWT_ENABLED, Boolean.TRUE)) {
			// Se obtiene el token
			String token = getToken(request);
			if (StringUtils.isBlank(token)) {
				log.debug("No se ha recibido token");
				SecurityContextHolder.clearContext();
				
			} else {
				// Se valida
				UsuarioDTO info = this.jwtService.verify(token);
				SecurityUtil.setAuthentication(info);
			}
			
		} else {
			setDummyLogin();		
		}
		
		filterChain.doFilter(request, response);
	}
	
	/**
	 * Obtiene el token de la peticion.
	 * 
	 * @param request
	 * @return
	 */
	private static String getToken(final HttpServletRequest request) {
		// Se obtiene la cabecera
		String header = request.getHeader(ConfiguracionUtil.getAsString(Propiedad.JWT_HEADER, HttpHeaders.AUTHORIZATION));
		// Se obtiene el token
		return getToken(header);
	}
	
	/**
	 * Obtiene el token de la cabecera.
	 * 
	 * @param header
	 * @return
	 */
	private static String getToken(final String header) {
		// Si no se ha recibido la cabecera
		if (StringUtils.isBlank(header))
			return null;
		
		// Se comprueba que comienza por el prefijo
		String prefix = ConfiguracionUtil.getAsString(Propiedad.JWT_HEADER_PREFIX);
		if (!StringUtils.startsWith(header, prefix))
			return null;
		
		// Se obtiene el token
		return StringUtils.substring(header, prefix.length()).trim();
	}

	/**
	 * Sets the dummy login.
	 */
	private static void setDummyLogin() {
		// Aquí estableces los detalles de la persona
		UsuarioDTO userInfo = new UsuarioDTO();
//		userInfo.setDni("99999999R");
		userInfo.setDocIdent("99999999R");
		userInfo.setNombre("PRUEBAS EIDAS");
		
		SecurityUtil.setAuthentication(userInfo);
	}

}
