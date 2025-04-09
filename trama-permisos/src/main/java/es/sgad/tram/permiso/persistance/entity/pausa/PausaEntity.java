package es.sgad.trama.permiso.persistance.entity.pausa;

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

import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "PAUSA")
public class PausaEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@Column(name = "HORA_INICIO")
	private LocalTime horaInicio;
	
	@Column(name = "HORA_FIN")
	private LocalTime horaFin;
	
	@Column(name = "TIEMPO_PAUSA")
	private LocalTime tiempoPausa;
	
	@Column(name = "RESTAR_PAUSA")
	private Integer restarPausa;
	
	@Column(name = "TIEMPO_EFECTIVO")
	private LocalTime tiempoEfectivo;
	
	
	@Column(name = "FECHA_BAJA")
	private LocalDate fechaBaja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_PAUSA", referencedColumnName = "ID")
	private TipoPausaEntity tipoPausa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TURNO", referencedColumnName = "ID")
	private TurnoEntity turno;
	
}