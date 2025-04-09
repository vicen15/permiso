package es.sgad.trama.permiso.persistance.entity.usuario.usuarioEdificioCalendarioFestivo;

import java.time.LocalDate;
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

import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivo.CalendarioFestivoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO_EDIFICIO_CALEN_FESTIVO")
public class UsuarioEdificioCalendarioFestivoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@Column(name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private LocalDate fechaFin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CALEN_FESTIVO", referencedColumnName = "ID")
	private CalendarioFestivoEntity calendarioFestivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
	private UsuarioEntity usuario;
	
}
