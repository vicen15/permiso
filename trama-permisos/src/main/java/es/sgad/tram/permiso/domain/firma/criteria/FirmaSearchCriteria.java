package es.sgad.trama.permiso.domain.firma.criteria;

import lombok.Data;

@Data
public class FirmaSearchCriteria {
	
	private String firmaCriptoSolicitante;

	private String firmaNoCriptoSolicitante;

	private String firmaCriptoAutorizador;

	private String firmaNoCriptoAutorizador;

//	private Boolean activo;

//	private Date fechaBaja;
	
	private int page = 0;
	
	private int size = 10;
}

