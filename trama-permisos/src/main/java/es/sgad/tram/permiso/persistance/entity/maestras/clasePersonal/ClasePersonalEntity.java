package es.sgad.trama.permiso.persistance.entity.maestras.clasePersonal;

import java.io.Serializable;
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
import es.sgad.trama.permiso.persistance.entity.usuario.extras.GrupoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "CLASE_PERSONAL")
public class ClasePersonalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1706610130553573069L;

	@Id
	@SequenceGenerator(name = "clase_personal_sequence", sequenceName = "CLASE_PERSONAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clase_personal_sequence")
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPCION", nullable = false, length = 250)
	private String descripcion;

	@OneToMany(mappedBy = "clasePersonal")
	private List<UsuarioEntity> listaUsuario;

	@OneToMany(mappedBy = "clasePersonal")
	private List<GrupoEntity> listaGrupo;

}