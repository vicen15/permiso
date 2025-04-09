package es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatronDias;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron.CalendarioPatronEntity;
import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "CALEN_PATRON_DIAS")
public class CalendarioPatronDiasEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CALEN_PATRON", referencedColumnName = "ID", nullable = false)
	private CalendarioPatronEntity calendarioPatron;
	
	@ManyToOne
	@JoinColumn(name = "ID_TURNO", referencedColumnName = "ID", nullable = false)
	private TurnoEntity turno;


	@Column(name = "FECHA", nullable = false)
	private Date fecha;
	
	
	
	
}