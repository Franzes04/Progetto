package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository.DomandeRepository;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.DomandeService;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.DomandeDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande}.
 */
@RestController
@RequestMapping("/api")
public class DomandeResource {

    private final Logger log = LoggerFactory.getLogger(DomandeResource.class);

    private static final String ENTITY_NAME = "domande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DomandeService domandeService;

    private final DomandeRepository domandeRepository;

    public DomandeResource(DomandeService domandeService, DomandeRepository domandeRepository) {
        this.domandeService = domandeService;
        this.domandeRepository = domandeRepository;
    }

    /**
     * {@code POST  /domandes} : Create a new domande.
     *
     * @param domandeDTO the domandeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new domandeDTO, or with status {@code 400 (Bad Request)} if the domande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/domandes")
    public ResponseEntity<DomandeDTO> createDomande(@RequestBody DomandeDTO domandeDTO) throws URISyntaxException {
        log.debug("REST request to save Domande : {}", domandeDTO);
        if (domandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new domande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DomandeDTO result = domandeService.save(domandeDTO);
        return ResponseEntity
            .created(new URI("/api/domandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /domandes/:id} : Updates an existing domande.
     *
     * @param id the id of the domandeDTO to save.
     * @param domandeDTO the domandeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domandeDTO,
     * or with status {@code 400 (Bad Request)} if the domandeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the domandeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/domandes/{id}")
    public ResponseEntity<DomandeDTO> updateDomande(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DomandeDTO domandeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Domande : {}, {}", id, domandeDTO);
        if (domandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, domandeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!domandeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DomandeDTO result = domandeService.update(domandeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, domandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /domandes/:id} : Partial updates given fields of an existing domande, field will ignore if it is null
     *
     * @param id the id of the domandeDTO to save.
     * @param domandeDTO the domandeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domandeDTO,
     * or with status {@code 400 (Bad Request)} if the domandeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the domandeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the domandeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/domandes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DomandeDTO> partialUpdateDomande(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DomandeDTO domandeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Domande partially : {}, {}", id, domandeDTO);
        if (domandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, domandeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!domandeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DomandeDTO> result = domandeService.partialUpdate(domandeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, domandeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /domandes} : get all the domandes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of domandes in body.
     */
    @GetMapping("/domandes")
    public List<DomandeDTO> getAllDomandes() {
        log.debug("REST request to get all Domandes");
        return domandeService.findAll();
    }

    /**
     * {@code GET  /domandes/:id} : get the "id" domande.
     *
     * @param id the id of the domandeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the domandeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/domandes/{id}")
    public ResponseEntity<DomandeDTO> getDomande(@PathVariable Long id) {
        log.debug("REST request to get Domande : {}", id);
        Optional<DomandeDTO> domandeDTO = domandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(domandeDTO);
    }

    /**
     * {@code DELETE  /domandes/:id} : delete the "id" domande.
     *
     * @param id the id of the domandeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/domandes/{id}")
    public ResponseEntity<Void> deleteDomande(@PathVariable Long id) {
        log.debug("REST request to delete Domande : {}", id);
        domandeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
