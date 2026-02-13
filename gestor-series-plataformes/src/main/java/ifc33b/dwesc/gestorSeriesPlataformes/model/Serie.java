package ifc33b.dwesc.gestorseriesplataformes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Series model. Represents a TV series that belongs to a streaming platform.
 */
@Entity
@Data
@Table(name = "serie")
public class Serie {

    // Constantes para validación
    private static final int MIN_TITLE_LENGTH = 3;
    private static final int MAX_TITLE_LENGTH = 25;

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Se requiere un titulo para la serie")
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH, message = "El título debe de tener entre 3 y 25 caracteres")
    @Column(nullable = false)
    private String titol;

    @NotBlank(message = "Se requiere un genero para la serie")
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH, message = "El genero debe de tener entre 3 y 25 caracteres")
    @Column(nullable = false)
    private String genere;

    @NotNull(message = "Se requiere plataforma")
    @ManyToOne
    @JoinColumn(name = "plataforma_id", nullable = false)
    private Plataforma plataforma;

    // Constructores
    public Serie() {
    }

    public Serie(final String titol, final String genere, final Plataforma plataforma) {
        this.titol = titol;
        this.genere = genere;
        this.plataforma = plataforma;
    }

}
