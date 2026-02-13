package ifc33b.dwesc.gestorseriesplataformes.exception;

public class PlataformaNotFoundException extends RuntimeException {
    public PlataformaNotFoundException(Long id) {
        super("No se ha encontrado la plataforma con la ID: " + id);
    }
}
