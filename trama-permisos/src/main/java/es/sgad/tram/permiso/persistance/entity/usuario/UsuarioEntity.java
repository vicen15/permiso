package es.sgad.trama.permiso.persistance.entity.usuario;

import java.sql.Date;
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
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPersonalDias.CalendarioPersonalDiasEntity;
import es.sgad.trama.permiso.persistance.entity.edificio.EdificioEntity;
import es.sgad.trama.permiso.persistance.entity.maestras.clasePersonal.ClasePersonalEntity;
import es.sgad.trama.permiso.persistance.entity.maestras.sexo.SexoEntity;
import es.sgad.trama.permiso.persistance.entity.maestras.tipoDocIdent.TipoDocIdentEntity;
import es.sgad.trama.permiso.persistance.entity.rol.rolUsuario.RolUsuarioEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.superior.SuperiorEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.extras.FlexibilidadTipoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.extras.GrupoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.extras.UnidadEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.usuarioCalendarioPatron.UsuarioCalendarioPatronEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_DOCIDENT", referencedColumnName = "ID", nullable = false)
	private TipoDocIdentEntity tipoDocIdent;

	@Column(name = "DOCIDENT", length = 9, nullable = false)
	private String docIdent;

	@Column(name = "TARJETA", length = 32, nullable = false)
	private String tarjeta;

	@Column(name = "NOMBRE", length = 40, nullable = false)
	private String nombre;

	@Column(name = "APELLIDO1", length = 40, nullable = false)
	private String apellido1;

	@Column(name = "APELLIDO2", length = 40)
	private String apellido2;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@ManyToOne
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;

	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;

	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;

	//TODO: añadir turno
	@ManyToOne
	@JoinColumn(name = "ID_SEXO", referencedColumnName = "ID", nullable = false)
	private SexoEntity sexo;

	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD", referencedColumnName = "ID", nullable = true)
	private UnidadEntity unidad;

	@ManyToOne
	@JoinColumn(name = "ID_CLASE_PERSONAL", referencedColumnName = "ID", nullable = false)
	private ClasePersonalEntity clasePersonal;

	@ManyToOne
	@JoinColumn(name = "ID_EDIFICIO", referencedColumnName = "ID", nullable = false)
	private EdificioEntity edificio;

	@ManyToOne
	@JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID", nullable = false)
	private GrupoEntity grupo;

	@Column(name = "FICHAR_OBLIGATORIO", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 1")
	private Boolean ficharObligatorio;

	@Column(name = "FICHAR_ORDENADOR", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 1")
	private Boolean ficharOrdenador;

	@Column(name = "CORTESIA_ACTIVO", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 1")
	private Boolean cortesiaActivo;

	@ManyToOne
	@JoinColumn(name = "ID_FLEXIBILIDAD_TIPO", referencedColumnName = "ID", nullable = false)
	private FlexibilidadTipoEntity flexibilidadTipo;

	// ?? no hay que crearlo como FK
	@Column(name = "ID_FLEXIBILIDAD_SUBTIPO")
	private Long idFlexibilidadSubtipo;

	// ?? no hay que crearlo como FK
	@Column(name = "ID_JORNADA_INTENSIVA_AMPLIADA_TIPO")
	private Long idJornadaIntensivaAmpliadaTipo;

	@Column(name = "FECHA_INI_FLEX")
	private Date fechaIniFlex;

	@Column(name = "FECHA_INI_INTENSIVA_AMPLIADA")
	private Date fechaIniIntensivaAmpliada;

	@Column(name = "ANIO_FIN_FLEX")
	private Date anioFinFlex;

	@Column(name = "ANIO_FIN_INTENSIVA_AMPLIADA")
	private Date anioFinIntensivaAmpliada;

	// FK?
	@Column(name = "ID_FILTRO_ADICIONAL")
	private Long idFiltroAdicional;
	
	@OneToMany(mappedBy = "usuario")
	private List<CalendarioPersonalDiasEntity> listaCalendarioPersonalDias;
	
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioCalendarioPatronEntity> listaUsuarioCalendarioPatron;
	
	@OneToMany(mappedBy = "usuario")
	private List<UsuarioEdificioCalendarioFestivoEntity> listaUsuarioEdificioCalendarioFestivo;

	
	@OneToMany(mappedBy = "usuario")
	private List<RolUsuarioEntity> listaRolUsuario;
	
	@OneToMany(mappedBy = "validador")
	private List<SolicitudPermisoEntity> listaValidador;
	
	@OneToMany(mappedBy = "autorizador")
	private List<SolicitudPermisoEntity> listaAutorizador;
	
	@OneToMany(mappedBy = "gestorPersonal")
	private List<SolicitudPermisoEntity> listaGestorPersonal;
	
	@OneToMany(mappedBy = "gestorPersonalAsignado")
	private List<SolicitudPermisoEntity> listaGestorPersonalAsignado;
	
	@OneToMany(mappedBy = "validador")
	private List<SuperiorEntity> listaValidadores;
	
	@OneToMany(mappedBy = "autorizador")
	private List<SuperiorEntity> listaAutorizadores;
	
	@OneToMany(mappedBy = "suplente")
	private List<SuperiorEntity> listaSuplentes;
	
	
	
	
	@OneToMany(mappedBy = "usuario")
	private List<SuperiorEntity> listaSuperiorUsuario;
	
	@OneToMany(mappedBy = "validador")
	private List<SuperiorEntity> listaSuperiorValidador;
	
	@OneToMany(mappedBy = "autorizador")
	private List<SuperiorEntity> listaSuperiorAutorizador;
	
	@OneToMany(mappedBy = "suplente")
	private List<SuperiorEntity> listaSuperiorSuplente;
	
	
}
