package es.sgad.trama.permiso.persistance.entity.ambito;


import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivo.CalendarioFestivoEntity;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron.CalendarioPatronEntity;
import es.sgad.trama.permiso.persistance.entity.edificio.EdificioEntity;
import es.sgad.trama.permiso.persistance.entity.rol.rolUsuario.RolUsuarioEntity;
import es.sgad.trama.permiso.persistance.entity.tipoHora.TipoHoraEntity;
import es.sgad.trama.permiso.persistance.entity.turno.TurnoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.extras.UnidadEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "Ambito")
public class AmbitoEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;

	@Column(name = "NOMBRE", length = 2000, nullable = false)
	private String nombre;
	
	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;
	
	@Column(name = "USUARIO_WS", length = 50)
	private String usuarioWs;
	
	@Column(name = "CONTRASEÑA_WS", length = 64)
	private String contraseniaWs;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	@Column(name = "FIRMA_SOLICITAR", precision = 1, nullable = false)
	private Long firmaSolicitar;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	@Column(name = "FIRMA_AUTORIZAR", precision = 1, nullable = false)
	private Long firmaAutorizar;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	@Column(name = "FIRMA_FICHAJE", precision = 1, nullable = false)
	private Long firmaFichaje;
	
	@Column(name = "FECHA_LIMITE_VACACIONES", nullable = false)
	private Date fechaLimiteVacaciones;
	
	@Column(name = "CORTESIA_ENTRADA")
	private Long cortesiaEntrada;
	
	@Column(name = "CORTESIA_SALIDA")
	private Long cortesiaSalida;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	@Column(name = "OPCION_VALIDAR_SOLICITUD", precision = 1, nullable = false)
	private Long opcionValidarSolicitud;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	@Column(name = "OPCION_VALIDAR_FECHA_LIMITE_VACACIONES", precision = 1, nullable = false)
	private Long opcionValidarFechaLimiteVacaciones;
	
//	Igual tendrian que ser un ENUM con las opciones predefinidas si son siempre las mismas y no se pueden cambiar
	@Column(name = "OPCION_LICENCIA_AAPP", precision = 1, nullable = false)
	private Long opcionLicenciaAAPP;
	
	@Column(name = "HORA_EMPIEZA_TARDE")
	private Date horaEmpiezaTarde;
	
	@Column(name = "AVISAR_AUSENCIAS", precision = 1, nullable = false)
	private Boolean avisarAusencias;
	
	@Column(name = "AVISAR_MARCAJES_IMPARES", precision = 1, nullable = false)
	private Boolean avisarMarcajesImpares;
	
	@Column(name = "PLANTILLA_BOLSA_CONCILIACION")
	private Blob plantillaBolsaConciliacion;
	
	@Column(name = "NUMERO_PERSONAS_INFORME_ASINCRONO", nullable = false)
	private Long numeroPersonasInformeAsincrono;
	
	@Column(name = "OBSERVACION", length = 2000)
	private String observacion;
	
//	 @Column(name = "ACTIVO")
//	    private Boolean activo;
//	    
//	    @Column(name = "FECHA_BAJA")
//		@Temporal(TemporalType.DATE)
//		private LocalDateTime fechaBaja;
	    
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<EdificioEntity> listaEdificios;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<CalendarioPatronEntity> listaCalendarioPatron;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<TurnoEntity> listaTurnos;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<TipoHoraEntity> listaTipoHoras;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<CalendarioFestivoEntity> listaCalendarioFestivo;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<UsuarioEntity> listaUsuario;
	
//	@OneToMany(mappedBy = "ambito", 
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "ambito")
	private List<UnidadEntity> listaUnidad;
	
	@OneToMany(mappedBy = "ambito")
	private List<RolUsuarioEntity> listaRolUsuario;
}