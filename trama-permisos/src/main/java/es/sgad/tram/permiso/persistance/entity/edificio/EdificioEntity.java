package es.sgad.trama.permiso.persistance.entity.edificio;

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
import es.sgad.trama.permiso.persistance.entity.calendario.calendarioFestivo.CalendarioFestivoEntity;
import es.sgad.trama.permiso.persistance.entity.maestras.husoHorario.HusoHorarioEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "EDIFICIO")
public class EdificioEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "ID_AMBITO", referencedColumnName = "ID", nullable = false)
	private AmbitoEntity ambito;

	@Column(name = "DIRECCION", length = 255, nullable = false)
	private String direccion;

	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;

	@Column(name = "ID_PROVINCIA", nullable = false)
	private Long idProvincia;

	@Column(name = "ID_MUNICIPIO", nullable = false)
	private Long idMunicipio;

	@ManyToOne
	@JoinColumn(name = "ID_HUSO_HORARIO", referencedColumnName = "ID")
	private HusoHorarioEntity husoHorario;

	// @OneToMany(mappedBy = "edificio",
	// orphanRemoval = true,
	// cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "edificio")
	private List<UsuarioEntity> listaUsuario;

	@OneToMany(mappedBy = "edificio")
	private List<CalendarioFestivoEntity> listaCalendarioFestivo;

}