package ifc33b.dwesc.gestorseriesplataformes.dto;

import ifc33b.dwesc.gestorseriesplataformes.model.Serie;
import lombok.Data;

/**
 * Response object for Serie entity.
 */
@Data
public class SerieResponse {

    // Atributos
    private Long id;
    private String titol;
    private String genere;
    private Long plataformaId;

    // Constructores
    public SerieResponse(Serie serie) {
        this.setId(serie.getId());
        this.setTitol(serie.getTitol());
        this.setGenere(serie.getGenere());
        this.setPlataformaId(serie.getPlataforma().getId());
    }
}
