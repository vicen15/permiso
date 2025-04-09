package es.sgad.trama.permiso.domain.tipoHora;

import java.sql.Date;
import java.util.List;

import es.sgad.trama.permiso.domain.franja.FranjaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoHoraDTO {

	private Long id;
	
	private String idAmbito;
	
	private String codigo;
	
	private String descripcion;
	
	private Date tiempoMin;
	
	private Date tiempoMax;
	
	private Date bloqueTiempo;
	
	private Date cambioRedondeo;
	
	private Integer efectiva;
	
//	private Boolean activo;
//	
//	private LocalDateTime fechaBaja;
	
	private List<FranjaDTO> listaFranjas;
	
	private Boolean obligatorio;	
	
}
