package es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivoDias;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivo.CalendarioFestivoEntity;
import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "CALEN_FESTIVO_DIAS")
public class CalendarioFestivoDiasEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@Column(name = "FECHA", nullable = false)
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CALEN_FESTIVO", referencedColumnName = "ID", nullable = false)
	private CalendarioFestivoEntity calendarioFestivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TURNO", referencedColumnName = "ID", nullable = false)
	private TurnoEntity turno;
	
}
