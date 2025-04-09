package es.sgad.trama.permiso.persistance.entity.usuario.usuarioCalendarioPatron;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron.CalendarioPatronEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO_CALEN_PATRON")
public class UsuarioCalendarioPatronEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CALEN_PATRON", referencedColumnName = "ID", nullable = false)
	private CalendarioPatronEntity calendarioPatron;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
	private UsuarioEntity usuario;

	@Column(name = "FECHA_INICIO", nullable = false)
	private LocalDate fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private LocalDate fechaFin;
		
}