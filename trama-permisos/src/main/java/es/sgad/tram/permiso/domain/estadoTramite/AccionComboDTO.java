package es.sgad.trama.permiso.domain.estadoTramite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccionComboDTO {

	private Long id;
	
	private String descripcion;
	
}
