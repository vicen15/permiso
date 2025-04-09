package es.sgad.trama.permiso.persistance.entity.franja;

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

import es.sgad.trama.permiso.persistance.entity.tipoHora.TipoHoraEntity;
import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "FRANJA")
public class FranjaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "HORA_INICIO", nullable = false)
	private Date horaInicio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TURNO", referencedColumnName = "ID", nullable = false)
	private TurnoEntity turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_HORA", referencedColumnName = "ID", nullable = false)
	private TipoHoraEntity tipoHora;

}
