package es.sgad.trama.permiso.domain.ambito.tipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoPermisoAmbitoComboDTO{
	
	private String id;
	
	private String descripcion;
	
}