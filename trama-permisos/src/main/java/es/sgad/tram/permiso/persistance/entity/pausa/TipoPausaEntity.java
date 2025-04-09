package es.sgad.trama.permiso.persistance.entity.pausa;

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
@Table(name = "TIPO_PAUSA")
public class TipoPausaEntity {

	@Id
    @SequenceGenerator(name = "tipo_pausa_sequence", sequenceName = "TIPO_PAUSA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_pausa_sequence")
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "DESCRIPCION"/*, nullable = false*/)
	private String descripcion;
	
	
	@OneToMany(mappedBy = "tipoPausa")
	private List<PausaEntity> listaPausas;
	
}
