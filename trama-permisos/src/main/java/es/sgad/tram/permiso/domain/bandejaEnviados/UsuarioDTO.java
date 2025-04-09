package es.sgad.trama.permiso.domain.bandejaEnviados;

import java.sql.Date;
import java.util.List;

import es.sgad.trama.permiso.domain.calendario.calendarioPersonalDias.CalendarioPersonalDiasDTO;
import es.sgad.trama.permiso.domain.rol.rolUsuario.RolUsuarioDTO;
import es.sgad.trama.permiso.domain.solicitudPermiso.SolicitudPermisoDTO;
import es.sgad.trama.permiso.domain.usuario.usuarioCalendarioPatron.UsuarioCalendarioPatronDTO;
import es.sgad.trama.permiso.domain.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

	private String id;

//	private TipoDocIdentDTO tipoDocIdent;
	private Long idTipoDocIdent;

	private String docIdent;
	private String tarjeta;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private String email;

//	private AmbitoDTO ambito;
	private String idAmbito;

	private Date fechaAlta;

//	private Boolean activo;
	
	private Date fechaBaja;
//	private SexoDTO sexo;
	private Long idSexo;

//	private UnidadDTO unidad;
//
//	private ClasePersonalDTO clasePersonal;
//
//	private EdificioDTO edificio;
//
//	private GrupoDTO grupo;
	private String idUnidad;

	private Long idClasePersonal;

	private String idEdificio;

	private Long idGrupo;

	private Boolean ficharObligatorio;

	private Boolean ficharOrdenador;

	private Boolean cortesiaActivo;

//	private FlexibilidadTipoDTO flexibilidadTipo;
	private Long idFlexibilidadTipo;

	private Long idFlexibilidadSubtipo;

	private Long idJornadaIntensivaAmpliadaTipo;

	private Date fechaIniFlex;

	private Date fechaIniIntensivaAmpliada;

	private Date anioFinFlex;

	private Date anioFinIntensivaAmpliada;

	private Long idFiltroAdicional;
	
	private List<CalendarioPersonalDiasDTO> listaCalendarioPersonalDias;
	
	private List<UsuarioCalendarioPatronDTO> listaUsuarioCalendarioPatron;
	
	private List<UsuarioEdificioCalendarioFestivoDTO> listaUsuarioEdificioCalendarioFestivo;

	private List<RolUsuarioDTO> listaRolUsuario;
	
	private List<SolicitudPermisoDTO> listaValidador;
	
	private List<SolicitudPermisoDTO> listaAutorizador;
	
	private List<SolicitudPermisoDTO> listaGestorPersonal;
	
	private List<SolicitudPermisoDTO> listaGestorPersonalAsignado;
	
	

}
