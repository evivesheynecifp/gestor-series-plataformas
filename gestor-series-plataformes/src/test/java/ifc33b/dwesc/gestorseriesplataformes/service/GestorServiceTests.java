package ifc33b.dwesc.gestorseriesplataformes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import ifc33b.dwesc.gestorseriesplataformes.dto.SerieRequest;
import ifc33b.dwesc.gestorseriesplataformes.exception.PlataformaNotFoundException;
import ifc33b.dwesc.gestorseriesplataformes.model.Plataforma;
import ifc33b.dwesc.gestorseriesplataformes.model.Serie;
import ifc33b.dwesc.gestorseriesplataformes.repository.PlataformaRepository;
import ifc33b.dwesc.gestorseriesplataformes.repository.SerieRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GestorServiceTest {

    @Mock
    private PlataformaRepository plataformaRepository;

    @Mock
    private SerieRepository serieRepository;

    @InjectMocks
    private GestorService gestorService;

    private Plataforma plataforma;

    @BeforeEach
    void setup() {
        plataforma = new Plataforma("Netflix");
        plataforma.setId(1L);
    }

    // getPlataformes()
    @Test
    @DisplayName("Should return list of plataformas")
    void shouldReturnPlataformes() {
        when(plataformaRepository.findAll())
                .thenReturn(List.of(plataforma));

        var result = gestorService.getPlataformes();

        assertEquals(1, result.size());
        verify(plataformaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no plataformas exist")
    void shouldReturnEmptyPlataformes() {
        when(plataformaRepository.findAll())
                .thenReturn(List.of());

        var result = gestorService.getPlataformes();

        assertTrue(result.isEmpty());
    }

    // getSeries()
    @Test
    @DisplayName("Should return series of a plataforma")
    void shouldReturnSeries() {
        Serie serie = new Serie("Dark", "SciFi", plataforma);
        when(serieRepository.getSeriesInPlataforma(1L))
                .thenReturn(List.of(serie));

        var result = gestorService.getSeries(1L);

        assertEquals(1, result.size());
        verify(serieRepository).getSeriesInPlataforma(1L);
    }

    @Test
    @DisplayName("Should return empty list when no series found")
    void shouldReturnEmptySeries() {
        when(serieRepository.getSeriesInPlataforma(1L))
                .thenReturn(List.of());

        var result = gestorService.getSeries(1L);

        assertTrue(result.isEmpty());
    }

    // createSerie()
    @Test
    @DisplayName("Should create new serie successfully")
    void shouldCreateSerie() {

        SerieRequest request = new SerieRequest("New Serie", "Drama", 1L);

        when(plataformaRepository.findById(1L))
                .thenReturn(Optional.of(plataforma));

        var response = gestorService.createSerie(request);

        assertNotNull(response);
        verify(serieRepository).save(any(Serie.class));
    }

    @Test
    @DisplayName("Should throw exception when plataforma not found")
    void shouldThrowWhenPlatformNotFound() {

        SerieRequest request = new SerieRequest("Test", "Drama", 99L);

        when(plataformaRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(PlataformaNotFoundException.class,
                () -> gestorService.createSerie(request));

        verify(serieRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should save serie with correct data")
    void shouldSaveCorrectSerieData() {

        SerieRequest request = new SerieRequest("Breaking Bad", "Drama", 1L);

        when(plataformaRepository.findById(1L))
                .thenReturn(Optional.of(plataforma));

        gestorService.createSerie(request);

        verify(serieRepository).save(argThat(serie
                -> serie.getTitol().equals("Breaking Bad")
                && serie.getGenere().equals("Drama")
                && serie.getPlataforma().equals(plataforma)
        ));
    }

    @Test
    @DisplayName("Should call findById exactly once")
    void shouldCallFindByIdOnce() {

        SerieRequest request = new SerieRequest("Serie", "Drama", 1L);

        when(plataformaRepository.findById(1L))
                .thenReturn(Optional.of(plataforma));

        gestorService.createSerie(request);

        verify(plataformaRepository, times(1)).findById(1L);
    }
}
