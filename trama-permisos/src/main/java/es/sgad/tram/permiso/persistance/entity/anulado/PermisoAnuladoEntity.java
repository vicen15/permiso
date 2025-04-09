package es.sgad.trama.permiso.persistance.entity.anulado;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;


@Data
@Entity
@Table(name = "PERMISO_ANULADO")
public class PermisoAnuladoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SOLICITUD_PERMISO", referencedColumnName = "ID", nullable = false)
	private SolicitudPermisoEntity solicitudPermiso;	
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PERMISO_AMBITO", referencedColumnName = "ID", nullable = false)
	private TipoPermisoAmbitoEntity tipoPermisoAmbito;	
	
	@ManyToOne
	@JoinColumn(name = "ID_SOLICITANTE", referencedColumnName = "ID", nullable = false)
	private UsuarioEntity usuarioSolicitante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SOLICITUD_ANULACION", referencedColumnName = "ID", nullable = false)
	private SolicitudPermisoEntity solicitudPermisoAnulacion;
	
	@Column(name = "FECHA_INICIO", nullable = false)
	private LocalDate fechaInicio;

	@Column(name = "FECHA_FIN")
	private LocalDate fechaFin;	
	
	@Column(name = "HORA_INICIO")
	private LocalTime horaInicio;

	@Column(name = "HORA_FIN")
	private LocalTime horaFin;
	
	@Column(name = "EJERCICIO", nullable = false)
	private Integer ejercicio;
	
	@Column(name = "ES_DIARIO", nullable = false)
	private Boolean esDiario;
	
}
