package es.sgad.trama.permiso.persistance.entity.usuario.extras;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.maestras.clasePersonal.ClasePersonalEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "GRUPO")
public class GrupoEntity {

	@Id
	@SequenceGenerator(name = "grupo_sequence", sequenceName = "GRUPO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupo_sequence")
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_CLASE_PERSONAL", referencedColumnName = "ID", nullable = false)
	private ClasePersonalEntity clasePersonal;

	@Column(name = "GRUPO", nullable = false, length = 2)
	private String grupo;

	@OneToMany(mappedBy = "grupo")
	private List<UsuarioEntity> listaUsuario;

}