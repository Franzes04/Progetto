package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Persona;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Persona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {}
