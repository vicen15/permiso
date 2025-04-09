package es.sgad.trama.permiso.persistance.entity.superior;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "SUPERIOR")
public class SuperiorEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	@Column(name="id")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY , optional=true)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
	private UsuarioEntity usuario;
 
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "ID_VALIDADOR", referencedColumnName = "ID", nullable = true)
	private UsuarioEntity validador;

	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "ID_AUTORIZADOR", referencedColumnName = "ID", nullable = true)
	private UsuarioEntity autorizador;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "ID_SUPLENTE", referencedColumnName = "ID", nullable = true)
	private UsuarioEntity suplente;
	
	@Column(name = "SUPLENCIA_VACACIONES", columnDefinition = "NUMBER(1,0) DEFAULT 0", nullable = false)
	private Boolean suplenciaVacaciones;

}
