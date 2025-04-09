package es.sgad.trama.permiso.persistance.entity.usuario.extras;

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
@Table(name = "FLEXIBILIDAD_TIPO")
public class FlexibilidadTipoEntity {

	@Id
	@SequenceGenerator(name = "flexibilidad_tipo_sequence", sequenceName = "FLEXIBILIDAD_TIPO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flexibilidad_tipo_sequence")
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPCION", nullable = false, length = 250)
	private String descripcion;

	@OneToMany(mappedBy = "flexibilidadTipo")
	private List<UsuarioEntity> listaUsuario;

}