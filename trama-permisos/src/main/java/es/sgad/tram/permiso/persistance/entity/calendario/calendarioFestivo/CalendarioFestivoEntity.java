package es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivoDias.CalendarioFestivoDiasEntity;
import es.sgad.trama.permiso.persistance.entity.edificio.EdificioEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.usuarioEdificioCalendarioFestivo.UsuarioEdificioCalendarioFestivoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "CALEN_FESTIVO")
public class CalendarioFestivoEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EDIFICIO", referencedColumnName = "ID", nullable = false)
	private EdificioEntity edificio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;
	
	@Column(name = "ANIO", nullable = false)
	private Date anio;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@OneToMany(mappedBy = "calendarioFestivo")
	private List<CalendarioFestivoDiasEntity> listaCalendarioFestivoDias;
	
	@OneToMany(mappedBy = "calendarioFestivo")
	private List<UsuarioEdificioCalendarioFestivoEntity> listaUsuarioEdificioCalendarioFestivo;

	
}
