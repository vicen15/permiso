package es.sgad.trama.permiso.domain.tipoOperacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoDuracionDTO {
	
	private Long id;
	
	private String descripcion;
	
//	private Boolean activo;
	
//	private Date fechaBaja;
	
//	private List<TipoIncidenciaDTO> listaIncidencia;
	
}
