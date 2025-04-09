package es.sgad.trama.permiso.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitanteComboDTO {
	
	private String id;

//	private Long idTipoDocIdent;

//	private String docIdent;

	private String nombreCompleto;

//	private String apellido1;

//	private String apellido2;

}
