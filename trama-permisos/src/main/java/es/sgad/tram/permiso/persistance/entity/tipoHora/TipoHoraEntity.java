package es.sgad.trama.permiso.persistance.entity.tipoHora;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.franja.FranjaEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_HORA")
public class TipoHoraEntity {

	@Id
    @SequenceGenerator(name = "tipo_hora_sequence", sequenceName = "TIPO_HORA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_hora_sequence")
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "CODIGO"/*, nullable = false*/)
	private String codigo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "TIEMPO_MIN")
	private Date tiempoMin;
	
	@Column(name = "TIEMPO_MAX")
	private Date tiempoMax;
	
	@Column(name = "BLOQUE_TIEMPO")
	private Date bloqueTiempo;
	
	@Column(name = "CAMBIO_REDONDEO")
	private Date cambioRedondeo;
	
	@Column(name = "EFECTIVA", nullable = false)
	private Integer efectiva;
	
	@OneToMany(mappedBy = "tipoHora")
	private List<FranjaEntity> listaFranjas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
    private AmbitoEntity ambito;
	
	@Column(name = "OBLIGATORIO", columnDefinition = "NUMBER(1,0) DEFAULT 1")
	private Boolean obligatorio;	
	
}
