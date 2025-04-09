package es.sgad.trama.permiso.persistance.entity;

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

import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;


@Data
@Entity
@Table(name = "PERMISO")
public class PermisoEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO_SOLICITANTE", referencedColumnName = "ID", nullable = false)
	private UsuarioEntity usuario;
	
	@Column(name = "ID_SOLICITUD_PERMISO", nullable = false)
	private String solicitudPermiso;
	
	@Column(name = "ID_TIPO_PERMISO_AMBITO", nullable = false)
	private String idTipoPermiso;
	
	@Column(name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private LocalDate fechaFin;

	@Column(name = "HORA_INICIO")
	private LocalTime horaInicio;

	@Column(name = "HORA_FIN")
	private LocalTime horaFin;
	
	@Column(name = "EJERCICIO", nullable = false)
	private Integer ejercicio;
	
	@Column(name = "NUM_DIAS", nullable = false)
	private Integer numeroDias;
	
	@Column(name = "ES_DIARIO", nullable = false)
	private Boolean esDiario;
}
