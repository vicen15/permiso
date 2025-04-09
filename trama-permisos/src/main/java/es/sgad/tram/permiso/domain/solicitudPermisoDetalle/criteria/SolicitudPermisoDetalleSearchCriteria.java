package es.sgad.trama.permiso.domain.solicitudPermisoDetalle.criteria;

import java.util.Date;

import lombok.Data;


//Hay que crear un *SearchCriteria por cada DTO sobre el que se desea buscar
// Es lo mismo que hay en el DTO pero añadiendo pagina y tamaño.
// Aqui se definen los campos por los que se puede filtrar en la busqueda
@Data
public class SolicitudPermisoDetalleSearchCriteria {


	private Long idSolicitudPermiso;

	private Date fechaInicio;

	private Date fechaFin;

	private Date horaInicio;

	private Date horaFin;

	private String observaciones;
	
	private int page = 0;
	    
	private int size = 10;
}
