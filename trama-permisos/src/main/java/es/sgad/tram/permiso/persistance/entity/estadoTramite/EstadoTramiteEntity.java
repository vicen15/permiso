package es.sgad.trama.permiso.persistance.entity.estadoTramite;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ESTADO_TRAMITE")
public class EstadoTramiteEntity {
	
	@Id
    @SequenceGenerator(name = "estado_tramite_sequence", sequenceName = "ESTADO_TRAMITE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_tramite_sequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRIPCION",  nullable = false)
    private String descripcion;
    
    @Column(name = "FECHA_BAJA",  nullable = true)
    private LocalDate fechaBaja;

    @Column(name = "FINAL",  nullable = false)
    private Integer estadoFinal;
    
    @Column(name = "PANTALLA",  nullable = true)
    private String descripcionPantalla;


}
