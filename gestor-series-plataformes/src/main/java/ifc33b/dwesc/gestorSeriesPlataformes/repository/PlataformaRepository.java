package ifc33b.dwesc.gestorseriesplataformes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifc33b.dwesc.gestorseriesplataformes.model.Plataforma;

/**
 * Repository to manage Plataforma entity in database.
 */
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
}
