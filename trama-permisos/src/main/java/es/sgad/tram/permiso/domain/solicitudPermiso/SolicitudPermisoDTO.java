package es.sgad.trama.permiso.domain.solicitudPermiso;

import java.time.LocalDate;
import java.util.List;

import es.sgad.trama.permiso.domain.solicitudPermisoDetalle.SolicitudPermisoDetalleDTO;
import es.sgad.trama.permiso.domain.solicitudPermisoDocumento.SolicitudPermisoDocumentoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudPermisoDTO{
	
	private String id;
	
    private String idTipoPermisoAmbito;
    
    private String idSolicitante;  
    
    private String idValidador; 
    
    private String idAutorizador;
    
    private String idGestorPersonal;
    
    private String idGpAsignado;
    
    private String idFirma;
	
    private Long idEstadoTramite;
    
	private String observacionesValidador;
	
	private String observacionesAutorizador;
	
	private Integer ejercicio;
	
	private String observacionesParaUsuario;
	
    private String motivoDenegacion;
    
    private LocalDate fechaSolicitud;
    
    private LocalDate fechaValidacion;
    
    private LocalDate fechaAutorizacion;
    
    private LocalDate fechaGestorPersonal;
    
    private LocalDate fechaPeticionDocumentacion;
    
    private LocalDate fechaDesestimacion;
    
    private LocalDate fechaDenegada;
    
    private Long idTipoOperacion;
    
    private Long validacionDelegada;
    
    private Long autorizacionDelegada;
    
    private String idPermisoAnular;
    
    private String observacionesGp;
    
    private String infoParaGp;
    
    private String idAmbito; 
    
    
    private List<SolicitudPermisoDetalleDTO> listaSolicitudPermisoDetalle;
    
    private List<SolicitudPermisoDocumentoDTO> listaSolicitudPermisoDocumento;
}
