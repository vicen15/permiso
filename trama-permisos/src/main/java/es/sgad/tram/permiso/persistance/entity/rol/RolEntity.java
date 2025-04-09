package es.sgad.trama.permiso.persistance.entity.rol;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.rol.rolUsuario.RolUsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "ROL")
public class RolEntity {
	
	@Id
    @SequenceGenerator(name = "rol_sequence", sequenceName = "ROL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_sequence")
    @Column(name = "ID")
    private Long id;
	

	@Column(name = "NOMBRE", length = 100, nullable = false)
	private String nombre;
	
	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;
	
	
	
	@OneToMany(mappedBy = "rol")
	private List<RolUsuarioEntity> listaRolUsuario;
	
	
}