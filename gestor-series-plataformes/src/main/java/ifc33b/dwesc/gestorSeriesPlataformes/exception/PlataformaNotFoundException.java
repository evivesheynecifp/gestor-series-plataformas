package ifc33b.dwesc.gestorseriesplataformes.exception;

/**
 * Custom exception for when a Plataforma is not found in the database.
 */
public class PlataformaNotFoundException extends RuntimeException {

    public PlataformaNotFoundException(Long id) {
        super("No se ha encontrado la plataforma con la ID: " + id);
    }
}
