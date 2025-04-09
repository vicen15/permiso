package es.sgad.trama.permiso.domain.tipoOperacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoOperacionComboDTO {
	
	private Long id;
	
	/** Valores: solicitud, anulación */
	private String descripcion;

}
