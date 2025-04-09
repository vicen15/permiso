//TODO Queda pendiente de añadir a futuro la gestion de incidencias

package es.sgad.trama.permiso.persistance.entity.incidencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "INCIDENCIA")
public class IncidenciaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	@Column(name="ID")
	private String id;
	
}
