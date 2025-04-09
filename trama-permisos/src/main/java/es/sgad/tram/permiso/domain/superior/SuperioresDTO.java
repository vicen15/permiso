package es.sgad.trama.permiso.domain.superior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuperioresDTO {
	
    private String idUsuario;
    
    private NombreSuperiorDTO validador;
    
    private NombreSuperiorDTO autorizador;
    
    
}