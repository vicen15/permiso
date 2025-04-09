package es.sgad.trama.permiso.domain.tipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoPermisoComboDTO{
	
	private Long id;
	
	private String descripcion;
	
}