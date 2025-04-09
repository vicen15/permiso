package es.sgad.trama.permiso.domain.rol.rolUsuario.criteria;

import lombok.Data;

@Data
public class RolUsuarioSearchCriteria {
	
	private Long idAmbito;

	private Long idUsuario;
	
	private Long idRol;
	
//	private Boolean activo;

//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
