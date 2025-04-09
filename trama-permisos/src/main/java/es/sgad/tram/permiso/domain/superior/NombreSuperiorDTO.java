package es.sgad.trama.permiso.domain.superior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NombreSuperiorDTO {
	
    private String idUsuario;
    
    private String nombre;
    
    private String apellido1;
    
    private String apellido2;
    
    
}