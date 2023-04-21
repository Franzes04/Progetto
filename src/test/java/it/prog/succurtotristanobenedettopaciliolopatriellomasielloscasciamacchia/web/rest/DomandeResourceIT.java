package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.IntegrationTest;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository.DomandeRepository;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.DomandeDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper.DomandeMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DomandeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DomandeResourceIT {

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/domandes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DomandeRepository domandeRepository;

    @Autowired
    private DomandeMapper domandeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDomandeMockMvc;

    private Domande domande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domande createEntity(EntityManager em) {
        Domande domande = new Domande().descrizione(DEFAULT_DESCRIZIONE);
        return domande;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domande createUpdatedEntity(EntityManager em) {
        Domande domande = new Domande().descrizione(UPDATED_DESCRIZIONE);
        return domande;
    }

    @BeforeEach
    public void initTest() {
        domande = createEntity(em);
    }

    @Test
    @Transactional
    void createDomande() throws Exception {
        int databaseSizeBeforeCreate = domandeRepository.findAll().size();
        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);
        restDomandeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeCreate + 1);
        Domande testDomande = domandeList.get(domandeList.size() - 1);
        assertThat(testDomande.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
    }

    @Test
    @Transactional
    void createDomandeWithExistingId() throws Exception {
        // Create the Domande with an existing ID
        domande.setId(1L);
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        int databaseSizeBeforeCreate = domandeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomandeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDomandes() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        // Get all the domandeList
        restDomandeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domande.getId().intValue())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE)));
    }

    @Test
    @Transactional
    void getDomande() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        // Get the domande
        restDomandeMockMvc
            .perform(get(ENTITY_API_URL_ID, domande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(domande.getId().intValue()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE));
    }

    @Test
    @Transactional
    void getNonExistingDomande() throws Exception {
        // Get the domande
        restDomandeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDomande() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();

        // Update the domande
        Domande updatedDomande = domandeRepository.findById(domande.getId()).get();
        // Disconnect from session so that the updates on updatedDomande are not directly saved in db
        em.detach(updatedDomande);
        updatedDomande.descrizione(UPDATED_DESCRIZIONE);
        DomandeDTO domandeDTO = domandeMapper.toDto(updatedDomande);

        restDomandeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, domandeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(domandeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
        Domande testDomande = domandeList.get(domandeList.size() - 1);
        assertThat(testDomande.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    void putNonExistingDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();
        domande.setId(count.incrementAndGet());

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomandeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, domandeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(domandeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();
        domande.setId(count.incrementAndGet());

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomandeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(domandeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();
        domande.setId(count.incrementAndGet());

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomandeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDomandeWithPatch() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();

        // Update the domande using partial update
        Domande partialUpdatedDomande = new Domande();
        partialUpdatedDomande.setId(domande.getId());

        partialUpdatedDomande.descrizione(UPDATED_DESCRIZIONE);

        restDomandeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDomande.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDomande))
            )
            .andExpect(status().isOk());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
        Domande testDomande = domandeList.get(domandeList.size() - 1);
        assertThat(testDomande.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    void fullUpdateDomandeWithPatch() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();

        // Update the domande using partial update
        Domande partialUpdatedDomande = new Domande();
        partialUpdatedDomande.setId(domande.getId());

        partialUpdatedDomande.descrizione(UPDATED_DESCRIZIONE);

        restDomandeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDomande.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDomande))
            )
            .andExpect(status().isOk());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
        Domande testDomande = domandeList.get(domandeList.size() - 1);
        assertThat(testDomande.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
    }

    @Test
    @Transactional
    void patchNonExistingDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();
        domande.setId(count.incrementAndGet());

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomandeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, domandeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(domandeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();
        domande.setId(count.incrementAndGet());

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomandeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(domandeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();
        domande.setId(count.incrementAndGet());

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDomandeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(domandeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDomande() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        int databaseSizeBeforeDelete = domandeRepository.findAll().size();

        // Delete the domande
        restDomandeMockMvc
            .perform(delete(ENTITY_API_URL_ID, domande.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
