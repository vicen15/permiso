package es.sgad.trama.permiso.api.rest.config.security.util;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.domain.usuario.PerfilType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utilidades para el acceso al contexto de seguridad.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtil {
	
	/**
	 * @return the authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * @param info the new authentication
	 */
	public static void setAuthentication(final UsuarioDTO info) {
		Authentication auth = new UsernamePasswordAuthenticationToken(info, null, createAuthorities(info));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	/**
	 * @return usuario autenticado
	 */
	public static UsuarioDTO getUserDetails() {
		Authentication auth = getAuthentication();
		return Objects.isNull(auth) || auth instanceof AnonymousAuthenticationToken ? null : (UsuarioDTO) auth.getPrincipal();
	}
	
	/**
	 * @return the authorities
	 */
	public static Collection<? extends GrantedAuthority> getAuthorities() {
		Authentication auth = getAuthentication();
		return Objects.isNull(auth) ? List.of() : auth.getAuthorities();
	}
	
	/**
	 * Crea un {@link GrantedAuthority} con el tipo de perfil.
	 * 
	 * @param perfil
	 * @return
	 */
	private static GrantedAuthority createAuthority(final PerfilType perfil) {
		return Objects.isNull(perfil) ? null : new SimpleGrantedAuthority(perfil.getCodigo());
	}
	
	/**
	 * Crea un listado de {@link GrantedAuthority} con los perfiles del usuario.
	 * 
	 * @param usuario
	 * @return
	 */
	private static List<GrantedAuthority> createAuthorities(final UsuarioDTO usuario) {
		return getPerfiles(usuario).stream().map(perfil -> createAuthority(perfil)).toList();
	}
	
	/**
	 * Crea un listado de {@link PerfilType} con los perfiles del usuario.
	 * 
	 * @param usuario
	 * @return
	 */
//	private static List<PerfilType> getPerfiles(final UsuarioDTO usuario) {
//		
//		if (Objects.isNull(usuario) || CollectionUtils.isEmpty(usuario.getPerfiles()))
//			return List.of();
//		
////		Si no entiendo mal esto tendria que ir a la tabla ROL_USUARIO y buscar por el ID de Usuario y supongo que luego habria que mirar lo que hay en la tabla ROL
//		
//		return usuario.getPerfiles().stream()
//			.filter(perfil -> Objects.nonNull(perfil.getPerfil()))
//			.map(perfil -> PerfilType.getByCodigo(perfil.getPerfil().getCod()))
//			.filter(perfil -> Objects.nonNull(perfil))
//			.toList();
//	}
	private static List<PerfilType> getPerfiles(final UsuarioDTO usuario) {
		
//		if (Objects.isNull(usuario) || CollectionUtils.isEmpty(usuario.getPerfiles()))
				PerfilType a = PerfilType.USUARIO;
			return List.of(a);
		
//		Si no entiendo mal esto tendria que ir a la tabla ROL_USUARIO y buscar por el ID de Usuario y supongo que luego habria que mirar lo que hay en la tabla ROL
		// pero aqui no se deberian meter llamadas a servicios creo por lo que habria que rehacer el UsuarioDTO para que incluyese la lista de RolUsuarios que esta en la entity
//		return usuario.getPerfiles().stream()
//			.filter(perfil -> Objects.nonNull(perfil.getPerfil()))
//			.map(perfil -> PerfilType.getByCodigo(perfil.getPerfil().getCod()))
//			.filter(perfil -> Objects.nonNull(perfil))
//			.toList();
	}

	/**
	 * Extrae el valor de la clave docIdent del string @WebRequest.getRemoteUser()
	 * @param remoteUser Informacion de usuario
	 * @return valor de la clave docIdent
	 */
	public static String extraerDocIdentFromRequestRemoteUser(String remoteUser) {
		String regex = "docIdent=([^,\\)]+)";
		Pattern pattern  = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(remoteUser);
		
		if(matcher.find()) {
			return matcher.group(1);
		}
		
		return null;
	}

}
