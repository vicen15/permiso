package es.sgad.trama.permiso.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.sgad.trama.permiso.domain.SearchPageResponse;

//public class PermisoSearchPageResponse<T>  implements PageResponse<T> {
public class PermisoSearchPageResponse<T>  extends SearchPageResponse{
	
	 	@JsonProperty("solicitudPermisoPendiente")
		private List<T> content;
}

