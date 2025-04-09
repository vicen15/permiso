package es.sgad.trama.permiso.service.usuario;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.sgad.trama.permiso.domain.bandejaEnviados.UsuarioDTO;
import es.sgad.trama.permiso.mapper.usuario.UsuarioMapper;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;
import es.sgad.trama.permiso.persistance.usuario.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	@Transactional(readOnly = true)
	@Override
	public UsuarioDTO getUsuarioById(String id) {
		UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElse(null);
		if (usuarioEntity != null && usuarioEntity.getAmbito() != null) {
			// This will trigger the lazy loading of the ambito entity
			usuarioEntity.getAmbito().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getClasePersonal() != null) {
			// This will trigger the lazy loading of the clase personal entity
			usuarioEntity.getClasePersonal().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getEdificio() != null) {
			// This will trigger the lazy loading of the Edificio entity
			usuarioEntity.getEdificio().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getFlexibilidadTipo() != null) {
			// This will trigger the lazy loading of the FlexibilidadTipo entity
			usuarioEntity.getFlexibilidadTipo().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getGrupo() != null) {
			// This will trigger the lazy loading of the Grupo entity
			usuarioEntity.getGrupo().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getSexo() != null) {
			// This will trigger the lazy loading of the Sexo entity
			usuarioEntity.getSexo().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getTipoDocIdent() != null) {
			// This will trigger the lazy loading of the TipoDocIdent entity
			usuarioEntity.getTipoDocIdent().getId();
		}
		if (usuarioEntity != null && usuarioEntity.getUnidad() != null) {
			// This will trigger the lazy loading of the unidad entity
			usuarioEntity.getUnidad().getId();
		}
		return this.usuarioMapper.toDomain(usuarioEntity);
	}


	@Override
	public UsuarioDTO getByDocIdent(String docIdent) {
		UsuarioEntity entity = StringUtils.isBlank(docIdent) ? null
				: this.usuarioRepository.findByDocIdentIgnoreCase(docIdent).orElse(null);
		return this.usuarioMapper.toDomain(entity);
	}


}