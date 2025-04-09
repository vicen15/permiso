package es.sgad.trama.permiso.persistance.superior;

/**
 * Filtro para la búsqueda de entidades `SuperiorEntity`.
 */
public record SuperiorFilter(
		
    String idUsuario,
    
    String idValidador,
    
    String idAutorizador,
    
    String idSuplente,
    
    Boolean suplenciaVacaciones
    
) {
	
}
