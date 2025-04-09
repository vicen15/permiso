package es.sgad.trama.permiso.persistance.entity.maestras.husoHorario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.edificio.EdificioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "HUSO_HORARIO")
public class HusoHorarioEntity {

	@Id
	@SequenceGenerator(name = "huso_horario_sequence", sequenceName = "HUSO_HORARIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "huso_horario_sequence")
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPCION", nullable = false, length = 250)
	private String descripcion;

	@Column(name = "VALOR", nullable = false)
	private Long valor;

	@Column(name = "ZONA", length = 500, nullable = false)
	private String zona;

	@OneToMany(mappedBy = "husoHorario")
	private List<EdificioEntity> listaEdificios;

}