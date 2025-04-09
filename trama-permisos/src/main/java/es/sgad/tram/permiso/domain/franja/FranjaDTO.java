package es.sgad.trama.permiso.domain.franja;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranjaDTO {

	private String id;
	
	private String idTurno;
	
	private Long idTipoHora;
	
	private Date horaInicio;
	
//	private Boolean activo;
//	
//	private LocalDateTime fechaBaja;
	
}
