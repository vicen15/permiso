package es.sgad.trama.permiso.domain.estadoTramite;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoTramiteDTO {

	private Long id;
	
	private String descripcion;
	
//	private Integer activo;
	
	private LocalDate fechaBaja;
	
}
