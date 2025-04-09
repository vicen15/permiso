package es.sgad.trama.permiso.persistance.entity.maestras.sexo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "SEXO")
public class SexoEntity {

	@Id
	@SequenceGenerator(name = "sexo_sequence", sequenceName = "SEXO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sexo_sequence")
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPCION", nullable = false, length = 250)
	private String descripcion;

	@OneToMany(mappedBy = "sexo")
	private List<UsuarioEntity> listaUsuario;

}