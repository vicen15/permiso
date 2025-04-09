package es.sgad.trama.permiso.persistance.entity.solicitudPermisoDocumento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "SOLICITUD_PERMISO_DOCUMENTO")
public class SolicitudPermisoDocumentoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_SOLICITUD_PERMISO", referencedColumnName = "ID", nullable = false)
	private SolicitudPermisoEntity solicitudPermiso;
	
	//TODO: SETEAR LIMITES PARA LAS VARIABLES EN LA ENTIDAD
	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "RUTA")
	private String ruta;
		
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "PESO")
	private Integer peso;
	
}
