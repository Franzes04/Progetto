package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository.DomandeRepository;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.DomandeDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper.DomandeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Domande}.
 */
@Service
@Transactional
public class DomandeService {

    private final Logger log = LoggerFactory.getLogger(DomandeService.class);

    private final DomandeRepository domandeRepository;

    private final DomandeMapper domandeMapper;

    public DomandeService(DomandeRepository domandeRepository, DomandeMapper domandeMapper) {
        this.domandeRepository = domandeRepository;
        this.domandeMapper = domandeMapper;
    }

    /**
     * Save a domande.
     *
     * @param domandeDTO the entity to save.
     * @return the persisted entity.
     */
    public DomandeDTO save(DomandeDTO domandeDTO) {
        log.debug("Request to save Domande : {}", domandeDTO);
        Domande domande = domandeMapper.toEntity(domandeDTO);
        domande = domandeRepository.save(domande);
        return domandeMapper.toDto(domande);
    }

    /**
     * Update a domande.
     *
     * @param domandeDTO the entity to save.
     * @return the persisted entity.
     */
    public DomandeDTO update(DomandeDTO domandeDTO) {
        log.debug("Request to update Domande : {}", domandeDTO);
        Domande domande = domandeMapper.toEntity(domandeDTO);
        domande = domandeRepository.save(domande);
        return domandeMapper.toDto(domande);
    }

    /**
     * Partially update a domande.
     *
     * @param domandeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DomandeDTO> partialUpdate(DomandeDTO domandeDTO) {
        log.debug("Request to partially update Domande : {}", domandeDTO);

        return domandeRepository
            .findById(domandeDTO.getId())
            .map(existingDomande -> {
                domandeMapper.partialUpdate(existingDomande, domandeDTO);

                return existingDomande;
            })
            .map(domandeRepository::save)
            .map(domandeMapper::toDto);
    }

    /**
     * Get all the domandes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DomandeDTO> findAll() {
        log.debug("Request to get all Domandes");
        return domandeRepository.findAll().stream().map(domandeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one domande by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DomandeDTO> findOne(Long id) {
        log.debug("Request to get Domande : {}", id);
        return domandeRepository.findById(id).map(domandeMapper::toDto);
    }

    /**
     * Delete the domande by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Domande : {}", id);
        domandeRepository.deleteById(id);
    }
}
