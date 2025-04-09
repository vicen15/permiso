package es.sgad.trama.permiso.persistance.entity.turno;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

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
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPatronDias.CalendarioPatronDiasEntity;
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioPersonalDias.CalendarioPersonalDiasEntity;
import es.sgad.trama.permiso.persistance.entity.franja.FranjaEntity;
import es.sgad.trama.permiso.persistance.entity.pausa.PausaEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "TURNO")
public class TurnoEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@Column(name = "CODIGO", nullable = false)
	private String codigo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "TURNO_24_HORAS", nullable = false)
	private Long turnoVeinticuatroHoras;
	
	@Column(name = "TIEMPO", nullable = false)
	private Date tiempo;
	
	@Column(name = "FESTIVO", columnDefinition = "NUMBER(1,0) DEFAULT 1", nullable = false)
	private Integer festivo;
	

//	// TODO: CAMBIAR A LOCALDATETIME
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;
	
	@OneToMany(mappedBy = "turno")
	private List<CalendarioPatronDiasEntity> listaCalendarioPatronDias;
	
	@OneToMany(mappedBy = "turno")
	private List<FranjaEntity> listaFranjas;
	
	@OneToMany(mappedBy = "turno")
	private List<CalendarioFestivoDiasEntity> listaCalendarioFestivoDias;
	
	@OneToMany(mappedBy = "turno")
	private List<CalendarioPersonalDiasEntity> listaCalendarioPersonalDias;
	
	@OneToMany(mappedBy = "turno")
	private List<PausaEntity> listaPausas;
	
}