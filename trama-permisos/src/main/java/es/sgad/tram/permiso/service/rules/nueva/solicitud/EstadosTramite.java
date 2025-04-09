package es.sgad.trama.permiso.service.rules.nueva.solicitud;

public enum EstadosTramite {
    PENDIENTE_DE_VALIDAR(1),
    PENDIENTE_DE_AUTORIZAR(2),
    PENDIENTE_DE_GESTOR_PERSONAL(3),
    ADMITIDA(5);

    private final long id;

    EstadosTramite(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

