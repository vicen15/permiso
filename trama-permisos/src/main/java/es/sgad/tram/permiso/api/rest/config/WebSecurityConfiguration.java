package es.sgad.trama.permiso.api.rest.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import es.sgad.trama.permiso.api.rest.config.security.filter.JWTAuthorizationFilter;
import es.sgad.trama.permiso.api.rest.config.security.util.ApiAuthenticationEntryPoint;
import es.sgad.trama.permiso.domain.usuario.PerfilType;
import lombok.RequiredArgsConstructor;

/**
 * Configuracion de seguridad.
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

	/** Filtro de control del token JWT */
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	
	@Bean
	AuthenticationEntryPoint authenticationEntryPoint() {
		return new ApiAuthenticationEntryPoint();
	}
	
	@Bean
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(List.of("*"));
		config.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
		config.setAllowedHeaders(List.of(
							HttpHeaders.AUTHORIZATION,
							HttpHeaders.CONTENT_TYPE,
							HttpHeaders.ACCEPT,
							HttpHeaders.ORIGIN,
							HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
							HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
							"X-Requested-With",
							"x-xsrf-token"));
		config.setExposedHeaders(List.of(
							HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
							HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		config.setAllowCredentials(true);
		config.setMaxAge(3600l);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    return http
	            .cors(cors -> cors.configurationSource(corsConfiguration())) // Configuración de CORS
	            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF, ya que usamos JWT
	            .exceptionHandling(config -> config.authenticationEntryPoint(this.authenticationEntryPoint())) // Manejo de excepciones
	            .headers(config -> config.frameOptions(options -> options.sameOrigin())) // Protección contra clickjacking
	            .formLogin(form -> form.disable()) // Deshabilitar el formulario de login
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin estado
	            .authorizeHttpRequests(auth ->
	                auth
	                    // Permitir solicitudes OPTIONS para CORS
	                	.antMatchers("/**").permitAll()  // Esto permite todas las solicitudes OPTIONS debido a CORS configurado

	                    // Actuator
	                    .antMatchers("/api/management/info").permitAll()
	                    .antMatchers("/api/management").hasAuthority(PerfilType.SUPER_ADMINISTRADOR.getCodigo())
	                    .antMatchers("/api/management/**").hasAuthority(PerfilType.SUPER_ADMINISTRADOR.getCodigo())

	                    // Documentación Swagger
	                    .antMatchers("/documentation/api-docs**").permitAll()
	                    .antMatchers("/documentation/api-docs/**").permitAll()
	                    .antMatchers("/documentation/ui**").permitAll()
	                    .antMatchers("/documentation/ui/**").permitAll()
	                    .antMatchers("/documentation/swagger-ui**").permitAll()
	                    .antMatchers("/documentation/swagger-ui/**").permitAll()

	                    // POC
	                    .antMatchers("/api/poc/non-secured").permitAll()

	                    // API ERROR
	                    .antMatchers("/error/**").permitAll()

	                    // Cualquier otra solicitud requiere autenticación
	                    .anyRequest().authenticated()
	            )
	            .addFilterAfter(this.jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class) // Agregar el filtro de JWT
	            .build();
	}
	
}
