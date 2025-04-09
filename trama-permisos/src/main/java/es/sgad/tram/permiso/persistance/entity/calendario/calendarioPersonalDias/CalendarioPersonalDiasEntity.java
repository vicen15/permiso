package es.sgad.trama.permiso.persistance.entity.calendario.calendarioPersonalDias;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "CALEN_PERSONAL_DIAS")
public class CalendarioPersonalDiasEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // v4 uuid
	private String id;

	@ManyToOne
	@JoinColumn(name = "ID_TURNO", referencedColumnName = "ID", nullable = false)
	private TurnoEntity turno;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
	private UsuarioEntity usuario;

	@Column(name = "FECHA", nullable = false)
	private LocalDate fecha;

}