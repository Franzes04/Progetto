package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.IntegrationTest;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Risposta;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.repository.RispostaRepository;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.RispostaService;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.RispostaDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper.RispostaMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RispostaResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class RispostaResourceIT {

    private static final String DEFAULT_RISPOSTE = "AAAAAAAAAA";
    private static final String UPDATED_RISPOSTE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/rispostas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RispostaRepository rispostaRepository;

    @Mock
    private RispostaRepository rispostaRepositoryMock;

    @Autowired
    private RispostaMapper rispostaMapper;

    @Mock
    private RispostaService rispostaServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRispostaMockMvc;

    private Risposta risposta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risposta createEntity(EntityManager em) {
        Risposta risposta = new Risposta().risposte(DEFAULT_RISPOSTE);
        return risposta;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risposta createUpdatedEntity(EntityManager em) {
        Risposta risposta = new Risposta().risposte(UPDATED_RISPOSTE);
        return risposta;
    }

    @BeforeEach
    public void initTest() {
        risposta = createEntity(em);
    }

    @Test
    @Transactional
    void createRisposta() throws Exception {
        int databaseSizeBeforeCreate = rispostaRepository.findAll().size();
        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);
        restRispostaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rispostaDTO)))
            .andExpect(status().isCreated());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeCreate + 1);
        Risposta testRisposta = rispostaList.get(rispostaList.size() - 1);
        assertThat(testRisposta.getRisposte()).isEqualTo(DEFAULT_RISPOSTE);
    }

    @Test
    @Transactional
    void createRispostaWithExistingId() throws Exception {
        // Create the Risposta with an existing ID
        risposta.setId(1L);
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        int databaseSizeBeforeCreate = rispostaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRispostaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rispostaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRispostas() throws Exception {
        // Initialize the database
        rispostaRepository.saveAndFlush(risposta);

        // Get all the rispostaList
        restRispostaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risposta.getId().intValue())))
            .andExpect(jsonPath("$.[*].risposte").value(hasItem(DEFAULT_RISPOSTE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRispostasWithEagerRelationshipsIsEnabled() throws Exception {
        when(rispostaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRispostaMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(rispostaServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRispostasWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(rispostaServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRispostaMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(rispostaRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getRisposta() throws Exception {
        // Initialize the database
        rispostaRepository.saveAndFlush(risposta);

        // Get the risposta
        restRispostaMockMvc
            .perform(get(ENTITY_API_URL_ID, risposta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(risposta.getId().intValue()))
            .andExpect(jsonPath("$.risposte").value(DEFAULT_RISPOSTE));
    }

    @Test
    @Transactional
    void getNonExistingRisposta() throws Exception {
        // Get the risposta
        restRispostaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRisposta() throws Exception {
        // Initialize the database
        rispostaRepository.saveAndFlush(risposta);

        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();

        // Update the risposta
        Risposta updatedRisposta = rispostaRepository.findById(risposta.getId()).get();
        // Disconnect from session so that the updates on updatedRisposta are not directly saved in db
        em.detach(updatedRisposta);
        updatedRisposta.risposte(UPDATED_RISPOSTE);
        RispostaDTO rispostaDTO = rispostaMapper.toDto(updatedRisposta);

        restRispostaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rispostaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rispostaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
        Risposta testRisposta = rispostaList.get(rispostaList.size() - 1);
        assertThat(testRisposta.getRisposte()).isEqualTo(UPDATED_RISPOSTE);
    }

    @Test
    @Transactional
    void putNonExistingRisposta() throws Exception {
        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();
        risposta.setId(count.incrementAndGet());

        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRispostaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rispostaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rispostaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRisposta() throws Exception {
        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();
        risposta.setId(count.incrementAndGet());

        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRispostaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rispostaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRisposta() throws Exception {
        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();
        risposta.setId(count.incrementAndGet());

        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRispostaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rispostaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRispostaWithPatch() throws Exception {
        // Initialize the database
        rispostaRepository.saveAndFlush(risposta);

        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();

        // Update the risposta using partial update
        Risposta partialUpdatedRisposta = new Risposta();
        partialUpdatedRisposta.setId(risposta.getId());

        restRispostaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRisposta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRisposta))
            )
            .andExpect(status().isOk());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
        Risposta testRisposta = rispostaList.get(rispostaList.size() - 1);
        assertThat(testRisposta.getRisposte()).isEqualTo(DEFAULT_RISPOSTE);
    }

    @Test
    @Transactional
    void fullUpdateRispostaWithPatch() throws Exception {
        // Initialize the database
        rispostaRepository.saveAndFlush(risposta);

        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();

        // Update the risposta using partial update
        Risposta partialUpdatedRisposta = new Risposta();
        partialUpdatedRisposta.setId(risposta.getId());

        partialUpdatedRisposta.risposte(UPDATED_RISPOSTE);

        restRispostaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRisposta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRisposta))
            )
            .andExpect(status().isOk());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
        Risposta testRisposta = rispostaList.get(rispostaList.size() - 1);
        assertThat(testRisposta.getRisposte()).isEqualTo(UPDATED_RISPOSTE);
    }

    @Test
    @Transactional
    void patchNonExistingRisposta() throws Exception {
        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();
        risposta.setId(count.incrementAndGet());

        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRispostaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rispostaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rispostaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRisposta() throws Exception {
        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();
        risposta.setId(count.incrementAndGet());

        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRispostaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rispostaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRisposta() throws Exception {
        int databaseSizeBeforeUpdate = rispostaRepository.findAll().size();
        risposta.setId(count.incrementAndGet());

        // Create the Risposta
        RispostaDTO rispostaDTO = rispostaMapper.toDto(risposta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRispostaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rispostaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Risposta in the database
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRisposta() throws Exception {
        // Initialize the database
        rispostaRepository.saveAndFlush(risposta);

        int databaseSizeBeforeDelete = rispostaRepository.findAll().size();

        // Delete the risposta
        restRispostaMockMvc
            .perform(delete(ENTITY_API_URL_ID, risposta.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Risposta> rispostaList = rispostaRepository.findAll();
        assertThat(rispostaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
