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

import lombok.Data;

@Data
@Entity
@Table(name = "UNIDAD_TIPO")
public class UnidadTipoEntity {
	

	@Id
    @SequenceGenerator(name = "unidad_tipo_sequence", sequenceName = "UNIDAD_TIPO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidad_tipo_sequence")
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "DESCRIPCION", nullable = false, length = 250)
	private String descripcion;
	
	@OneToMany(mappedBy = "unidadTipo")
	private List<UnidadEntity> listaUnidad;
}