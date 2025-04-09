package es.sgad.trama.permiso.service.bandejaPendientes;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaPendientes.PantallaBandejaPendientesDTO;
import es.sgad.trama.permiso.domain.bandejaPendientes.SolicitudPermisoPendienteDTO;
import es.sgad.trama.permiso.persistance.bandejaPendientes.FiltroBandejaPendientesFilter;

public interface BandejaPendientesService {
	
	public PantallaBandejaPendientesDTO getPantallaBandejaPendientes(String idUsuario, String idAmbito);
	
	public PantallaBandejaPendientesDTO getPantallaBandejaPendientesError(String mensajeError);
	
	SearchPageResponse<SolicitudPermisoPendienteDTO> searchBandejaPendientes(FiltroBandejaPendientesFilter filter,
			int page, int size);
}
