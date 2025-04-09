package es.sgad.trama.permiso.persistance.entity.tipoDuracion;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_DURACION")
public class TipoDuracionEntity {
	
	@Id
    @SequenceGenerator(name = "tipo_duracion_sequence", sequenceName = "TIPO_DURACION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_duracion_sequence")
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "DESCRIPCION", length = 16, nullable = false)
	private String descripcion;
	

}
