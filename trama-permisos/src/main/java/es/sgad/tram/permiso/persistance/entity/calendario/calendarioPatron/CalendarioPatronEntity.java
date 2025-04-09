package es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatron;


import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatronDias.CalendarioPatronDiasEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.usuarioCalendarioPatron.UsuarioCalendarioPatronEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "CALEN_PATRON")
public class CalendarioPatronEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;


	@Column(name = "ANIO", nullable = false)
	private Date anio;
	
	@Column(name = "NOMBRE", length = 255, nullable = false)
	private String nombre;
	
	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;
	
	
	@OneToMany(mappedBy = "calendarioPatron")
	private List<CalendarioPatronDiasEntity> listaCalendarioPatronDias;
	
	@OneToMany(mappedBy = "calendarioPatron")
	private List<UsuarioCalendarioPatronEntity> listaUsurioCalendarioPatron;
	
}