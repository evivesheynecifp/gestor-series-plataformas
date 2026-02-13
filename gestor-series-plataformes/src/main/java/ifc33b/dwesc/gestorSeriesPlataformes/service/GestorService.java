package ifc33b.dwesc.gestorseriesplataformes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifc33b.dwesc.gestorseriesplataformes.dto.PlataformaResponse;
import ifc33b.dwesc.gestorseriesplataformes.dto.SerieRequest;
import ifc33b.dwesc.gestorseriesplataformes.dto.SerieResponse;
import ifc33b.dwesc.gestorseriesplataformes.exception.PlataformaNotFoundException;
import ifc33b.dwesc.gestorseriesplataformes.model.Plataforma;
import ifc33b.dwesc.gestorseriesplataformes.model.Serie;
import ifc33b.dwesc.gestorseriesplataformes.repository.PlataformaRepository;
import ifc33b.dwesc.gestorseriesplataformes.repository.SerieRepository;

@Service
public final class GestorService {

    private final PlataformaRepository plataformaRepository;
    private final SerieRepository serieRepository;

    @Autowired
    public GestorService(PlataformaRepository plataformaRepository, SerieRepository serieRepository) {
        this.plataformaRepository = plataformaRepository;
        this.serieRepository = serieRepository;
    }

    // Devuelve todas las plataformas
    public List<PlataformaResponse> getPlataformes() {
        return plataformaRepository.findAll().stream()
                .map(PlataformaResponse::new)
                .collect(Collectors.toList());
    }

    // Devuelve las series de una plataforma
    public List<SerieResponse> getSeries(Long id) {
        return serieRepository.getSeriesInPlataforma(id).stream()
                .map(SerieResponse::new)
                .collect(Collectors.toList());
    }

    // Agregar una nueva serie a una plataforma
    public SerieResponse createSerie(SerieRequest request) {
        Plataforma plataforma = plataformaRepository.findById(request.getPlataformaId())
                .orElseThrow(() -> new PlataformaNotFoundException(request.getPlataformaId()));

        Serie serie = new Serie(request.getTitol(), request.getGenere(), plataforma);
        serieRepository.save(serie);
        return new SerieResponse(serie);
    }
}
