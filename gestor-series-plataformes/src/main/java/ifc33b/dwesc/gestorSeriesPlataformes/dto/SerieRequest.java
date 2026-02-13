package ifc33b.dwesc.gestorseriesplataformes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SerieRequest {

    // Constantes para validación
    private static final int MIN_TITLE_LENGTH = 3;
    private static final int MAX_TITLE_LENGTH = 25;

    // Atributos
    @NotBlank(message = "Se requiere un titulo para la serie")
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH, message = "El título debe de tener entre 3 y 25 caracteres")
    private String titol;

    @NotBlank(message = "Se requiere un genero para la serie")
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH, message = "El genero debe de tener entre 3 y 25 caracteres")
    private String genere;

    @NotNull(message = "Se requiere plataforma")
    private Long plataformaId;

    // Constructor
    public SerieRequest(String titulo, String genere, Long plataformaId) {
        this.setTitol(titulo);
        this.setGenere(genere);
        this.setPlataformaId(plataformaId);
    }
}
