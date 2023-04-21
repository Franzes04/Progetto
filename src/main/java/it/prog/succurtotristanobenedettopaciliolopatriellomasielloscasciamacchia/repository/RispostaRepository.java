package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Risposta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Risposta entity.
 */
@Repository
public interface RispostaRepository extends JpaRepository<Risposta, Long> {
    default Optional<Risposta> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Risposta> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Risposta> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct risposta from Risposta risposta left join fetch risposta.domande left join fetch risposta.persona",
        countQuery = "select count(distinct risposta) from Risposta risposta"
    )
    Page<Risposta> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct risposta from Risposta risposta left join fetch risposta.domande left join fetch risposta.persona")
    List<Risposta> findAllWithToOneRelationships();

    @Query(
        "select risposta from Risposta risposta left join fetch risposta.domande left join fetch risposta.persona where risposta.id =:id"
    )
    Optional<Risposta> findOneWithToOneRelationships(@Param("id") Long id);
}
