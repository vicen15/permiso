package es.sgad.trama.permiso.persistance.entity.error;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "EXCEPCION")
public class ErrorLogEntity {

	@Id
  //  @SequenceGenerator(name = "turno_sequence", sequenceName = "TURNO_SEQ", allocationSize = 1)
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @Column(name = "ID", nullable = false)
    private String id;
		
	@Column(name = "FECHA_HORA", columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime fechaHora;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
	private UsuarioEntity usuario;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "CODE")
	private String codigo;
	
	@Column(name = "MESSAGE")
	private String mensaje;
	
	@Column(name = "RUTA")
	private String ruta;
	
	@Column(name = "TRACE")
	private String trace;
	
//	El nombre que va despues del mappedBy tiene que ser igual al nombre de la variable que tenga la relacion @ManyToOne en la Entidad de la cual hay una lista
	@OneToMany(mappedBy = "errorLog")
	private List<ErrorValidacionEntity> listaValidaciones;
		
}