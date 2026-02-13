package ifc33b.dwesc.gestorseriesplataformes.dto;

import ifc33b.dwesc.gestorseriesplataformes.model.Plataforma;
import lombok.Data;

@Data
public class PlataformaResponse {

    // Atributos
    private Long id;
    private String nom;

    // Constructor
    public PlataformaResponse(Plataforma plataforma) {
        this.setId(plataforma.getId());
        this.setNom(plataforma.getNom());
    }
}
