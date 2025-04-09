package es.sgad.trama.permiso.domain.superior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SuperiorDTO {
	
	private String id;
	
    private String idUsuario;
    
    private String idValidador;
    
    private String idAutorizador;
    
    private String idSuplente;
    
    private Boolean suplenciaVacaciones;
    
}