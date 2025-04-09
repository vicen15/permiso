package es.sgad.trama.permiso.domain.calendario.calendarioPersonalDias;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioPersonalDiasDTO {

	private String id;

	private String idUsuario;

	private String idTurno;

	private LocalDate fecha;

}