package es.sgad.trama.permiso.persistance.entity.solicitudPermiso;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.PermisoEntity;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.estadoTramite.EstadoTramiteEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle.SolicitudPermisoDetalleEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoDocumento.SolicitudPermisoDocumentoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermisoFirma.SolicitudPermisoFirmaEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.tipoOperacion.TipoOperacionEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "SOLICITUD_PERMISO")
public class SolicitudPermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PERMISO_AMBITO", referencedColumnName = "ID", nullable = false)
    private TipoPermisoAmbitoEntity tipoPermisoAmbito;

//  @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID", nullable = false)
//  private PermisoEntity permiso;    
    
    @ManyToOne
    @JoinColumn(name = "ID_SOLICITANTE", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity solicitante;

    //TODO Queda pendiente revisar si los validadore, Autorizadores, GP...pueden ser nulos 
    @ManyToOne(optional=true)
    @JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID", nullable = true)
    private UsuarioEntity validador;

    @ManyToOne(optional=true)
    @JoinColumn(name = "ID_AUTORIZADOR", referencedColumnName = "ID", nullable = true)
    private UsuarioEntity autorizador;

    @ManyToOne(optional=true)
    @JoinColumn(name = "ID_GESTOR_PERSONAL", referencedColumnName = "ID", nullable = true)
    private UsuarioEntity gestorPersonal;

    @ManyToOne(optional=true)
    @JoinColumn(name = "ID_GP_ASIGNADO", referencedColumnName = "ID", nullable = true)
    private UsuarioEntity gestorPersonalAsignado;

    @Column(name = "MOTIVO_DENEGACION")
    private String motivoDenegacion;

    @Column(name = "FECHA_SOLICITUD", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(name = "FECHA_VALIDACION")
    private LocalDate fechaValidacion;
    
    @Column(name = "FECHA_AUTORIZACION")
    private LocalDate fechaAutorizacion;
    
    @Column(name = "FECHA_GESTOR_PERSONAL")
    private LocalDate fechaGestorPersonal;

    @Column(name = "FECHA_PETICION_DOCUMENTACION")
    private LocalDate fechaPeticionDocumentacion;

    @Column(name = "FECHA_DESESTIMACION")
    private LocalDate fechaDesestimacion;    
    
    @Column(name = "FECHA_DENEGADA")
    private LocalDate fechaDenegada;
    
    //TODO Consultar si una firma puede ser nula, por ejemplo, que este pendiente de firmar
    @ManyToOne(optional=true)
    @JoinColumn(name = "ID_FIRMA", referencedColumnName = "ID", nullable = true)
    private SolicitudPermisoFirmaEntity firma;
    
    @Column(name = "OBSERVACIONES_VALIDADOR")
    private String observacionesValidador;

    @Column(name = "OBSERVACIONES_AUTORIZADOR")
    private String observacionesAutorizador;

    @Column(name = "EJERCICIO")
    private Integer ejercicio;
    
    @Column(name = "OBSERVACIONES_PARA_USUARIO", length = 4000)
    private String observacionesParaUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_TRAMITE", referencedColumnName = "ID", nullable = false)
    private EstadoTramiteEntity estadoTramite;

    @Column(name = "OBSERVACIONES_GP", length = 4000)
    private String observacionesGp;

    @Column(name = "INFO_PARA_GP", length = 4000)
    private String infoParaGp;
    
    @ManyToOne(optional=false)
    @JoinColumn(name = "ID_AMBITO", nullable = false)
    private AmbitoEntity ambito;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_OPERACION", referencedColumnName = "ID", nullable = false)
    private TipoOperacionEntity tipoOperacion;

    @OneToOne(optional=true)
    @JoinColumn(name = "ID_SOLICITUD_ANULAR", referencedColumnName = "ID")
    private PermisoEntity permisoAnular;    
    
    // El nombre que va despues del mappedBy tiene que ser igual al nombre de la variable 
    // que tenga la relacion @ManyToOne en la Entidad de la cual hay una lista
    @OneToMany(mappedBy = "solicitudPermiso")
    private List<SolicitudPermisoDetalleEntity> listaSolicitudPermisoDetalle;
    
    @OneToMany(mappedBy = "solicitudPermiso")
    private List<SolicitudPermisoDocumentoEntity> listaSolicitudPermisoDocumento;
    

    
}