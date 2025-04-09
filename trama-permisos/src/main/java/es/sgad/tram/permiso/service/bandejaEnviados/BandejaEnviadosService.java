package es.sgad.trama.permiso.service.bandejaEnviados;

import java.util.List;

import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.bandejaEnviados.PantallaBandejaEnviadosDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoEnviadoDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosFilter;

public interface BandejaEnviadosService {
	
	// Devuelve los datos de la pantalla de bandeja de permisos enviados de un usuario para un ambito
	public PantallaBandejaEnviadosDTO getPantallaBandejaEnviados(String UsuarioId, String AmbitoId);
	
	// Devuelve el error producido al intentar cargar la pantalla de bandeja de permisos enviados de un usuario para un ambito
	public PantallaBandejaEnviadosDTO getPantallaBandejaEnviadosError(String mensajeError);
	
	// Devuelve la lista de solicitudes de permisos enviados filtrados por las condiciones marcadas por los campos de la busqueda
	SearchPageResponse<SolicitudPermisoEnviadoDTO> searchBandejaEnviados(FiltroBandejaEnviadosFilter filter,
			int page, int size);
	
	// Devuelve la lista de los solicitantes solicitudes de permisos enviados filtradas por las condiciones marcadas por los campos de la busqueda
	public List<SolicitanteComboDTO> getListaSolicitantes (FiltroBandejaEnviadosFilter filter, int page, int size);
	
	//List<SolicitudIncidenciaDetalleEnviadaDTO>  getBandejaEnviadasByAmbitoId(Long AmbitoId);
	
	//BandejaPendientesDTO saveSolicitudIncidenciaDetalle(SolicitudIncidenciaDetalleDTO solicitudInicdenciaDetalleDTO);

	//void deleteSolicitudIncidenciaDetalle(Long id);
	
	//List<SolicitudIncidenciaDetallePendienteDTO> getAllBandejaPendientes();
	
	//SolicitudIncidenciaDetalleDTO modificaSolicitudIncidenciaDetalle(Long id, SolicitudIncidenciaDetalleDTO solicitudIncidenciaDetalleDTO);
	
}
