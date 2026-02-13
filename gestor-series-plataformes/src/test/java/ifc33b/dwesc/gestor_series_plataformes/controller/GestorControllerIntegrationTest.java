package ifc33b.dwesc.gestor_series_plataformes.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class GestorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // GET /api/plataformes
    @Test
    @DisplayName("Should return all plataformas")
    void shouldReturnAllPlataformes() throws Exception {
        mockMvc.perform(get("/api/plataformes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    @DisplayName("Plataforma should contain Netflix")
    void shouldContainNetflix() throws Exception {
        mockMvc.perform(get("/api/plataformes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").exists());
    }

    // GET /api/series/plataforma/{id}
    @Test
    @DisplayName("Should return series for platform 1")
    void shouldReturnSeriesForPlatform1() throws Exception {
        mockMvc.perform(get("/api/series/plataforma/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    @DisplayName("Should return empty list for non-existing platform")
    void shouldReturnEmptyForInvalidPlatform() throws Exception {
        mockMvc.perform(get("/api/series/plataforma/999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // GET /api/series/plataforma/{id} - invalid ID formats
    @Test
    @DisplayName("Should return 400 for invalid platform ID format")
    void shouldReturn400ForInvalidPlatformIdFormat() throws Exception {
        mockMvc.perform(get("/api/series/plataforma/abc"))
                .andExpect(status().isBadRequest());
    }

    // GET /api/series/plataforma/{id} - verify single series returned
    @Test
    @DisplayName("Should return a single series for platform 10")
    void shouldReturnSingleSeriesForPlatform10() throws Exception {
        mockMvc.perform(get("/api/series/plataforma/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].titol").value("Attack Titan"));
    }

    // POST /api/series
    @Test
    @DisplayName("Should create a new serie")
    void shouldCreateSerie() throws Exception {

        String newSerie = """
                {
                    "titol": "New Serie",
                    "genere": "Drama",
                    "plataformaId": 1
                }
                """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newSerie))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titol").value("New Serie"))
                .andExpect(jsonPath("$.genere").value("Drama"));
    }

    // POST /api/series - Missing required fields
    @Test
    @DisplayName("Should fail when title is missing")
    void shouldFailWhenTitleMissing() throws Exception {

        String invalidSerie = """
                {
                    "genere": "Drama",
                    "plataformaId": 1
                }
                """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail when genere is missing")
    void shouldFailWhenGenereMissing() throws Exception {

        String invalidSerie = """
                {
                    "title": "New Serie",
                    "plataformaId": 1
                }
                """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail when plataforma is missing")
    void shouldFailWhenPlatformMissing() throws Exception {

        String invalidSerie = """
                {
                    "titol": "New Serie",
                    "genere": "Drama"
                }
                """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    // POST /api/series - Field length validations (Short)
    @Test
    @DisplayName("Should fail when title is too short")
    void shouldFailWhenTitleTooShort() throws Exception {

        String invalidSerie = """
                {
                    "titol": "AB",
                    "genere": "Drama",
                    "plataformaId": 1
                }
                """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail when genre is too short")
    void shouldFailWhenGenreTooShort() throws Exception {

        String invalidSerie = """
                {
                    "titol": "SerieTest",
                    "genere": "A",
                    "plataformaId": 1
                }
                """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    // POST /api/series - Field length validations (Long)
    @Test
    @DisplayName("Should fail when title is too long")
    void shouldFailWhenTitleTooLong() throws Exception {
        String invalidSerie = """
            {
                "titol": "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "genere": "Drama",
                "plataformaId": 1
            }
            """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should fail when genre is too long")
    void shouldFailWhenGenreTooLong() throws Exception {
        String invalidSerie = """
            {
                "titol": "Valid Title",
                "genere": "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "plataformaId": 1
            }
            """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidSerie))
                .andExpect(status().isBadRequest());
    }

    // POST /api/series - Duplicate title on same platform
    @Test
    @DisplayName("Should allow creating series with same title on different platform")
    void shouldAllowDuplicateTitleDifferentPlatform() throws Exception {
        String newSerie = """
            {
                "titol": "Dark",
                "genere": "SciFi",
                "plataformaId": 2
            }
            """;

        mockMvc.perform(post("/api/series")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newSerie))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titol").value("Dark"))
                .andExpect(jsonPath("$.genere").value("SciFi"));
    }
}
