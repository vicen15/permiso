package es.sgad.trama.permiso.domain.usuario;

import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Perfiles.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PerfilType {
	
	SUPER_ADMINISTRADOR ("SA"),
	INSPECTOR ("I"),
	CONTROL_EFECTIVOS ("CE"),
	PERFIL_ACCESIBLE ("PA"),
	EDITOR_CALENDARIO_FESTIVOS ("Festivos Locales"),
	FESTIVOS ("F"),
	CONSULTOR_INFORMES ("CI"),
	USUARIO ("U")
	;
	
	
	/** Codigo */
	private final String codigo;
	
	
	/**
	 * @param codigo
	 * @return perfil con el codigo indicado
	 */
	public static PerfilType getByCodigo(final String codigo) {
		return Stream.of(PerfilType.values())
				.filter(type -> type.getCodigo().equalsIgnoreCase(codigo))
				.findFirst()
				.orElse(null);
	}

}
