package es.sgad.trama.permiso.service.superior;

import es.sgad.trama.permiso.domain.superior.NombreSuperiorDTO;

public interface SuperiorService {

    /**
     * Recupera los autorizador dado un id de Usuario
     * @param id
     * @return SuperioresDTO
     */
    NombreSuperiorDTO getAutorizadorByIdUsuario(String idUsuario);
    
    /**
     * Recupera los validador dado un id de Usuario
     * @param id
     * @return SuperioresDTO
     */
    NombreSuperiorDTO getValidadorByIdUsuario(String idUsuario);
}