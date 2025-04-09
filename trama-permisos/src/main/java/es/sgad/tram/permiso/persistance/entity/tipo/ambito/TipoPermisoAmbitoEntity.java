package es.sgad.trama.permiso.persistance.entity.tipo.ambito;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.tipo.TipoPermisoEntity;
import lombok.Data;


@Data
@Entity
@Table(name = "TIPO_PERMISO_AMBITO")
public class TipoPermisoAmbitoEntity {

	@Id
	@Column(name = "ID")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_PERMISO", referencedColumnName = "ID", nullable = false)
	private TipoPermisoEntity tipoPermiso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;	
	
	
	@Column(name = "DESCRIPCION", length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "EJERCICIO", length = 100, nullable = false)
	private Integer ejercicio;

	@Column(name = "ES_TIEMPO_COMPUTABLE", nullable = true)
	private Boolean esTiempoComputable;	

	@Column(name = "SE_PUEDE_SOLICITAR", nullable = true)
	private Boolean sePuedeSolicitar;
	
	@Column(name = "DEBE_TENER_ADJUNTO", nullable = true)
	private Boolean debeTenerAdjunto;
	
	@Column(name = "CREADA_POR_ORGANISMO", nullable = true)
	private Boolean creadaPorOrganismo;

	@Column(name = "INFORMACION", nullable = true)
	private String informacion;	
					
	@Column(name = "ACTIVO_VALIDAR_AUTORIZAR", nullable = true)
	private Integer activoValidarAutorizar;
	
	@Column(name = "ACTIVO_GESTOR_PERSONAL", nullable = true)
	private Boolean activoGestorPersonal;	

	@Column(name = "APROBAR_EN_AMBITO_PADRE", nullable = true)
	private Integer aprobarEnAmbitoPadre;	
	
	@Column(name = "FECHA_BAJA", nullable = true)
	private LocalDate fechaBaja;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoPermisoAmbito")
	private List<SolicitudPermisoEntity> listaSolicitudPermiso;
	
}
