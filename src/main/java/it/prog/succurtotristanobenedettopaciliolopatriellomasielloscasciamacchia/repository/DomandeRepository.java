package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Domande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomandeRepository extends JpaRepository<Domande, Long> {}
