package es.sgad.trama.permiso.persistance.entity.rol.rolUsuario;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.rol.RolEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "ROL_USUARIO")
public class RolUsuarioEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
	private UsuarioEntity usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_ROL", referencedColumnName = "ID", nullable = false)
	private RolEntity rol;
	
	
}