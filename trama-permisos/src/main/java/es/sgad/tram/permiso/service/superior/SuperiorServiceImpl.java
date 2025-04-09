package es.sgad.trama.permiso.service.superior;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sgad.trama.permiso.domain.superior.NombreSuperiorDTO;
import es.sgad.trama.permiso.persistance.superior.SuperiorRepository;

@Service
public class SuperiorServiceImpl implements SuperiorService {

    @Autowired
    private SuperiorRepository superiorRepository;

	@Override
	public NombreSuperiorDTO getAutorizadorByIdUsuario(String idUsuario) {
		return superiorRepository.findAutorizadorForUsuario(UUID.fromString(idUsuario));
	}

	@Override
	public NombreSuperiorDTO getValidadorByIdUsuario(String idUsuario) {
		return superiorRepository.findValidadorForUsuario(UUID.fromString(idUsuario));
	}
}
