package es.sgad.trama.permiso.persistance.entity.solicitudPermisoDetalle;

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
import lombok.Data;

@Data
@Entity
@Table(name = "SOLICITUD_PERMISO_DETALLE")
public class SolicitudPermisoDetalleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SOLICITUD_PERMISO")
	private SolicitudPermisoEntity solicitudPermiso;


	@Column(name = "FECHA_INICIO", nullable = false)
	private LocalDate fechaInicio;

	@Column(name = "FECHA_FIN")
	private LocalDate fechaFin;	
	
	@Column(name = "HORA_INICIO")
	private LocalTime horaInicio;

	@Column(name = "HORA_FIN")
	private LocalTime horaFin;
	
	@Column(name = "OBSERVACIONES", length = 4000)
	private String observaciones;
	
}
