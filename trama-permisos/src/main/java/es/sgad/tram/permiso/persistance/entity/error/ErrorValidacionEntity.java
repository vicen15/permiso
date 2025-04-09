package es.sgad.trama.permiso.persistance.entity.error;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "EXCEPCION_VALIDACION")
public class ErrorValidacionEntity {

	@Id
  //  @SequenceGenerator(name = "turno_sequence", sequenceName = "TURNO_SEQ", allocationSize = 1)
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    @Column(name = "ID", nullable = false)
    private String id;
	
/*	@Column(name = "ERROR_ID", nullable = false)
	private String error_id;*/
	
	@Column(name = "FECHA_HORA", columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime fechaHora;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ERROR_ID", referencedColumnName = "ID")
	private ErrorLogEntity errorLog;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
	private UsuarioEntity usuario;
		
	@Column(name = "FIELD")
	private String campo;
	
	@Column(name = "CODE")
	private String codigo;
	
	@Column(name = "MESSAGE")
	private String mensaje;
		
}