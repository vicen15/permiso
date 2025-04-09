package es.sgad.trama.permiso.persistance.entity.tipoOperacion;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import lombok.Data;
@Data
@Entity
@Table(name = "TIPO_OPERACION")
public class TipoOperacionEntity {
	@Id
    @SequenceGenerator(name = "tipo_operacion_sequence", sequenceName = "TIPO_OPERACION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_operacion_sequence")
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "DESCRIPCION", length = 16, nullable = false)
	private String descripcion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoOperacion")
	private List<SolicitudPermisoEntity> listaSolicitudPermisos; 
}
