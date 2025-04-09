package es.sgad.trama.permiso.persistance.entity.solicitudPermisoFirma;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "SOLICITUD_PERMISO_FIRMA")
public class SolicitudPermisoFirmaEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//v4 uuid
	private String id;

	@Column(name = "FIRMA_CRIPTO_SOLICITANTE", nullable = true)
	@Lob
	private String firmaCriptoSolicitante;

	@Column(name = "FIRMA_NOCRIPTO_SOLICITANTE", nullable = true)
	@Lob
	private String firmaNoCriptoSolicitante;

	@Column(name = "FIRMA_CRIPTO_AUTORIZADOR", nullable = true)
	@Lob
	private String firmaCriptoAutorizador;

	@Column(name = "FIRMA_NOCRIPTO_AUTORIZADOR", nullable = true)
	@Lob
	private String firmaNoCriptoAutorizador;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "firma")
	private List<SolicitudPermisoEntity> listaSolicitudPermiso;
}
