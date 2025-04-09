package es.sgad.trama.service.permiso.bandejaEnviados;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import es.sgad.trama.common.utils.Constantes;
import es.sgad.trama.permiso.domain.SearchPageResponse;
import es.sgad.trama.permiso.domain.ambito.tipo.TipoPermisoAmbitoComboDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.PantallaBandejaEnviadosDTO;
import es.sgad.trama.permiso.domain.bandejaEnviados.SolicitudPermisoEnviadoDTO;
import es.sgad.trama.permiso.domain.estadoTramite.AccionComboDTO;
import es.sgad.trama.permiso.domain.estadoTramite.EstadoTramiteComboDTO;
import es.sgad.trama.permiso.domain.usuario.SolicitanteComboDTO;
import es.sgad.trama.permiso.mapper.bandejaEnviados.SolicitudPermisoEnviadoMapper;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.ambito.AmbitoRepository;
import es.sgad.trama.permiso.persistance.ambito.tipo.TipoPermisoAmbitoRepository;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosFilter;
import es.sgad.trama.permiso.persistance.bandejaEnviados.FiltroBandejaEnviadosRepository;
import es.sgad.trama.permiso.persistance.entity.ambito.AmbitoEntity;
import es.sgad.trama.permiso.persistance.entity.solicitudPermiso.SolicitudPermisoEntity;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.estadoTramite.EstadoTramiteRepository;
import es.sgad.trama.permiso.persistance.usuario.UsuarioRepository;
import es.sgad.trama.permiso.service.bandejaEnviados.BandejaEnviadosServiceImpl;

class BandejaEnviadosServiceTest {

    @Mock
    private FiltroBandejaEnviadosRepository bandejaEnviadosRepository;
    
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
    @Mock
    private EstadoTramiteRepository estadoTramiteRepository;
    
    @Mock
    private TipoPermisoAmbitoRepository tipoPermisoAmbitoRepository;
    
    @Mock
    private SolicitudPermisoEnviadoMapper solicitudPermisoEnviadoMapper;
    
    @Mock
    private UsuarioMapper usuarioMapper;
    
	//TODO ELIMINAR SERVICE DE PROYECTO PERMISO Y HACER LLAMADA HTTP AL SERVICIO QUE CORRESPONDA
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @Mock
    private AmbitoRepository ambitoRepository;
    
    @InjectMocks
    private BandejaEnviadosServiceImpl bandejaEnviadosService;

    private final String TEST_USER_ID = UUID.randomUUID().toString();
    private final String TEST_AMBITO_ID = UUID.randomUUID().toString();
    private final String TEST_USER_NAME = "test User Name";
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPantallaBandejaEnviados() {
    	
    	when(estadoTramiteRepository.findEstadoFinalForCombo()).thenReturn(List.of(new EstadoTramiteComboDTO(5L, "Admitida")));	
        when(estadoTramiteRepository.findAccionForCombo()).thenReturn(List.of(new AccionComboDTO(1L, "validar")));

        AmbitoEntity ambito = new AmbitoEntity();
        when(ambitoRepository.findById(TEST_AMBITO_ID)).thenReturn(Optional.of(ambito));
        
        List<TipoPermisoAmbitoComboDTO> listaTipoPermisoAmbito = List.of(new TipoPermisoAmbitoComboDTO("TEST_PERMISO_ID", "Permiso A"));
        when(tipoPermisoAmbitoRepository.findTipoPermisoAmbitoForCombo(ambito)).thenReturn(listaTipoPermisoAmbito);

        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.findById("TEST_USER_ID")).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toSolicitanteComboDTO(usuario)).thenReturn(new SolicitanteComboDTO(TEST_USER_ID, "test User Name"));
        
        List<SolicitudPermisoEntity> listaSolicitudes = List.of(new SolicitudPermisoEntity());
        when(bandejaEnviadosRepository.findListaSolicitudPermisoEnviado(TEST_AMBITO_ID, TEST_USER_ID)).thenReturn(listaSolicitudes);
        
        List<SolicitudPermisoEnviadoDTO> listaDTOs = List.of(new SolicitudPermisoEnviadoDTO());
        when(solicitudPermisoEnviadoMapper.toListaSolicitudPermisoEnviadoDTO(listaSolicitudes, Constantes.VALOR_STRING_CREADAS_POR_MI)).thenReturn(listaDTOs);

        PantallaBandejaEnviadosDTO result = bandejaEnviadosService.getPantallaBandejaEnviados(TEST_USER_ID, TEST_AMBITO_ID);

        // Verificar resultados
        assertNotNull(result);
        assertEquals(3, result.getEstadoCombo().size());
        assertEquals(1, result.getTipoPermisoAmbitoCombo().size());
        assertEquals(1, result.getSolicitudPermisoEnviado().size());
    }

    @Test
    void testGetPantallaBandejaEnviadosError() {
        PantallaBandejaEnviadosDTO result = bandejaEnviadosService.getPantallaBandejaEnviadosError("Error message");
        assertNotNull(result);
    }

    @Test
    void testSearchBandejaEnviados() {
        FiltroBandejaEnviadosFilter filter = new FiltroBandejaEnviadosFilter(TEST_USER_ID, null, null, null, null, null, null, null);
        List<SolicitudPermisoEntity> listaSolicitud = List.of(new SolicitudPermisoEntity());
        Page<SolicitudPermisoEntity> pageResult = new PageImpl<>(listaSolicitud);
        
        when(bandejaEnviadosRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(pageResult);
        when(solicitudPermisoEnviadoMapper.toSolicitudPermisoPageResponse(pageResult, filter.idAccion()))
            .thenReturn(new SearchPageResponse<>());

        SearchPageResponse<SolicitudPermisoEnviadoDTO> result = bandejaEnviadosService.searchBandejaEnviados(filter, 0, 10);
        
        assertNotNull(result);
    }
    
    @Test
    void testGetListaSolicitantes() {
    	FiltroBandejaEnviadosFilter filter = new FiltroBandejaEnviadosFilter(TEST_USER_ID, null, null, null, null, null, null, null);
    	SolicitudPermisoEntity solicitudPermisoEntity = new SolicitudPermisoEntity();
    	UsuarioEntity solicitante = new UsuarioEntity();
    	solicitudPermisoEntity.setSolicitante(solicitante);
    	List<SolicitudPermisoEntity> listaSolicitud = List.of(solicitudPermisoEntity);
        Page<SolicitudPermisoEntity> pageResult = new PageImpl<>(listaSolicitud);
        
        when(usuarioMapper.toSolicitanteComboDTO(solicitante)).thenReturn(new SolicitanteComboDTO(TEST_USER_ID, TEST_USER_NAME));
        when(bandejaEnviadosRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(pageResult);
        List<SolicitanteComboDTO> result = bandejaEnviadosService.getListaSolicitantes(filter, 0, 10);
        assertNotNull(result);
    }
}
