package es.sgad.trama.permiso.mapper.error;

import org.mapstruct.Named;
import es.sgad.trama.permiso.persistance.entity.usuario.UsuarioEntity;

public class UsuarioMapperHelper {

    @Named("mapUsuarioId")
    public static UsuarioEntity mapUsuarioId(String id) {
        if (id == null) return null;
        UsuarioEntity u = new UsuarioEntity();
        u.setId(id);
        return u;
    }
}
