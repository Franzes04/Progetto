package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository.RispostaRepository;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.RispostaService;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.RispostaDTO;
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
 * REST controller for managing {@link it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Risposta}.
 */
@RestController
@RequestMapping("/api")
public class RispostaResource {

    private final Logger log = LoggerFactory.getLogger(RispostaResource.class);

    private static final String ENTITY_NAME = "risposta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RispostaService rispostaService;

    private final RispostaRepository rispostaRepository;

    public RispostaResource(RispostaService rispostaService, RispostaRepository rispostaRepository) {
        this.rispostaService = rispostaService;
        this.rispostaRepository = rispostaRepository;
    }

    /**
     * {@code POST  /rispostas} : Create a new risposta.
     *
     * @param rispostaDTO the rispostaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rispostaDTO, or with status {@code 400 (Bad Request)} if the risposta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rispostas")
    public ResponseEntity<RispostaDTO> createRisposta(@RequestBody RispostaDTO rispostaDTO) throws URISyntaxException {
        log.debug("REST request to save Risposta : {}", rispostaDTO);
        if (rispostaDTO.getId() != null) {
            throw new BadRequestAlertException("A new risposta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RispostaDTO result = rispostaService.save(rispostaDTO);
        return ResponseEntity
            .created(new URI("/api/rispostas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rispostas/:id} : Updates an existing risposta.
     *
     * @param id the id of the rispostaDTO to save.
     * @param rispostaDTO the rispostaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rispostaDTO,
     * or with status {@code 400 (Bad Request)} if the rispostaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rispostaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rispostas/{id}")
    public ResponseEntity<RispostaDTO> updateRisposta(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RispostaDTO rispostaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Risposta : {}, {}", id, rispostaDTO);
        if (rispostaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rispostaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rispostaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RispostaDTO result = rispostaService.update(rispostaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rispostaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rispostas/:id} : Partial updates given fields of an existing risposta, field will ignore if it is null
     *
     * @param id the id of the rispostaDTO to save.
     * @param rispostaDTO the rispostaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rispostaDTO,
     * or with status {@code 400 (Bad Request)} if the rispostaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rispostaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rispostaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rispostas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RispostaDTO> partialUpdateRisposta(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RispostaDTO rispostaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Risposta partially : {}, {}", id, rispostaDTO);
        if (rispostaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rispostaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rispostaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RispostaDTO> result = rispostaService.partialUpdate(rispostaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rispostaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /rispostas} : get all the rispostas.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rispostas in body.
     */
    @GetMapping("/rispostas")
    public List<RispostaDTO> getAllRispostas(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Rispostas");
        return rispostaService.findAll();
    }

    /**
     * {@code GET  /rispostas/:id} : get the "id" risposta.
     *
     * @param id the id of the rispostaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rispostaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rispostas/{id}")
    public ResponseEntity<RispostaDTO> getRisposta(@PathVariable Long id) {
        log.debug("REST request to get Risposta : {}", id);
        Optional<RispostaDTO> rispostaDTO = rispostaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rispostaDTO);
    }

    /**
     * {@code DELETE  /rispostas/:id} : delete the "id" risposta.
     *
     * @param id the id of the rispostaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rispostas/{id}")
    public ResponseEntity<Void> deleteRisposta(@PathVariable Long id) {
        log.debug("REST request to delete Risposta : {}", id);
        rispostaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
