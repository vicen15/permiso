package es.sgad.trama.permiso.persistance.entity.usuario.extras;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "UNIDAD")
public class UnidadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@Column(name = "NOMBRE", nullable = false, length = 255)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_SUPERIOR", referencedColumnName = "ID", nullable = false)
	private UnidadEntity unidad;

	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_TIPO", referencedColumnName = "ID", nullable = false)
	private UnidadTipoEntity unidadTipo;

	@ManyToOne
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;

	@OneToMany(mappedBy = "unidad")
	private List<UsuarioEntity> listaUsuario;

}