package es.sgad.trama.permiso.persistance.entity.tipo;

import java.time.LocalDate;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.sgad.trama.permiso.persistance.entity.tipo.ambito.TipoPermisoAmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.tipoDuracion.TipoDuracionEntity;
import lombok.Data;


@Data
@Entity
@Table(name = "TIPO_PERMISO")
public class TipoPermisoEntity {

	@Id
    @SequenceGenerator(name = "tipo_permiso_sequence", sequenceName = "TIPO_PERMISO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_permiso_sequence")
    @Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_DURACION", referencedColumnName = "ID", nullable = false)
	private TipoDuracionEntity tipoDuracion;
	
	@Column(name = "DESCRIPCION", length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "ORDEN", nullable = false)
	private Integer orden;
	
	@Column(name = "COMPROBAR_PLAZO", nullable = false)
	private Boolean comprobarPlazo;
	
	@Column(name = "ES_TIEMPO_COMPUTABLE", nullable = true)
	private Boolean esTiempoComputable;
	
	@Column(name = "SE_PUEDE_SOLICITAR", nullable = true)
	private Boolean sePuedeSolicitar;
	
	@Column(name = "DEBE_TENER_ADJUNTO", nullable = true)
	private Boolean debeTenerAdjunto;
	
	@Column(name = "CREADA_POR_ORGANISMO", nullable = true)
	private Boolean creadaPorOrganismo;
	
	@Column(name = "CREAR_EN_AMBITO_NUEVO", nullable = true)
	private Boolean crearEnAmbitoNuevo;
	
	@Column(name = "FECHA_BAJA", nullable = true)
	private LocalDate fechaBaja;
	
	@Column(name = "CODIGO", length = 4, nullable = true)
	private String codigo;
	
	@OneToMany(mappedBy = "tipoPermiso")
	private List<TipoPermisoAmbitoEntity> listaTipoPermisoAmbito;
}
