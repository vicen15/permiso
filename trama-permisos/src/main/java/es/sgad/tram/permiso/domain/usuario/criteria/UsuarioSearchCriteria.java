package es.sgad.trama.permiso.domain.usuario.criteria;

import java.sql.Date;

import lombok.Data;

@Data
public class UsuarioSearchCriteria {

	private Long idTipoDocIdent;

	private String docIdent;

	private String tarjeta;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private String email;

	private Long idAmbito;

	private Date fechaAlta;

	private Date fechaBaja;

	private Long idSexo;

	private Long idUnidad;

	private Long idClasePersonal;

	private Long idEdificio;

	private Long idGrupo;

	private Boolean ficharObligatorio;

	private Boolean ficharOrdenador;

	private Boolean cortesiaActivo;

	private Long idFlexibilidadTipo;

	private Long idFlexibilidadSubtipo;

	private Long idJornadaIntensivaAmpliadaTipo;

	private Date fechaIniFlex;

	private Date fechaIniIntensivaAmpliada;

	private Date anioFinFlex;

	private Date anioFinIntensivaAmpliada;

	private Long idFiltroAdicional;

//	private Boolean activo;

//	private Date fechaBaja;

	private int page = 0;
	private int size = 10;
}
