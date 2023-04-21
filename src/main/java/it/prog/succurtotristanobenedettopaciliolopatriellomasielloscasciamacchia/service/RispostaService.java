package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Risposta;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository.RispostaRepository;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.RispostaDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper.RispostaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Risposta}.
 */
@Service
@Transactional
public class RispostaService {

    private final Logger log = LoggerFactory.getLogger(RispostaService.class);

    private final RispostaRepository rispostaRepository;

    private final RispostaMapper rispostaMapper;

    public RispostaService(RispostaRepository rispostaRepository, RispostaMapper rispostaMapper) {
        this.rispostaRepository = rispostaRepository;
        this.rispostaMapper = rispostaMapper;
    }

    /**
     * Save a risposta.
     *
     * @param rispostaDTO the entity to save.
     * @return the persisted entity.
     */
    public RispostaDTO save(RispostaDTO rispostaDTO) {
        log.debug("Request to save Risposta : {}", rispostaDTO);
        Risposta risposta = rispostaMapper.toEntity(rispostaDTO);
        risposta = rispostaRepository.save(risposta);
        return rispostaMapper.toDto(risposta);
    }

    /**
     * Update a risposta.
     *
     * @param rispostaDTO the entity to save.
     * @return the persisted entity.
     */
    public RispostaDTO update(RispostaDTO rispostaDTO) {
        log.debug("Request to update Risposta : {}", rispostaDTO);
        Risposta risposta = rispostaMapper.toEntity(rispostaDTO);
        risposta = rispostaRepository.save(risposta);
        return rispostaMapper.toDto(risposta);
    }

    /**
     * Partially update a risposta.
     *
     * @param rispostaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RispostaDTO> partialUpdate(RispostaDTO rispostaDTO) {
        log.debug("Request to partially update Risposta : {}", rispostaDTO);

        return rispostaRepository
            .findById(rispostaDTO.getId())
            .map(existingRisposta -> {
                rispostaMapper.partialUpdate(existingRisposta, rispostaDTO);

                return existingRisposta;
            })
            .map(rispostaRepository::save)
            .map(rispostaMapper::toDto);
    }

    /**
     * Get all the rispostas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RispostaDTO> findAll() {
        log.debug("Request to get all Rispostas");
        return rispostaRepository.findAll().stream().map(rispostaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the rispostas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RispostaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return rispostaRepository.findAllWithEagerRelationships(pageable).map(rispostaMapper::toDto);
    }

    /**
     * Get one risposta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RispostaDTO> findOne(Long id) {
        log.debug("Request to get Risposta : {}", id);
        return rispostaRepository.findOneWithEagerRelationships(id).map(rispostaMapper::toDto);
    }

    /**
     * Delete the risposta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Risposta : {}", id);
        rispostaRepository.deleteById(id);
    }
}
