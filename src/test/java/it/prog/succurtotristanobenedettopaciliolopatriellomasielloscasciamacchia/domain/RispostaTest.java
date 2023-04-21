package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain;

import static org.assertj.core.api.Assertions.assertThat;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RispostaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Risposta.class);
        Risposta risposta1 = new Risposta();
        risposta1.setId(1L);
        Risposta risposta2 = new Risposta();
        risposta2.setId(risposta1.getId());
        assertThat(risposta1).isEqualTo(risposta2);
        risposta2.setId(2L);
        assertThat(risposta1).isNotEqualTo(risposta2);
        risposta1.setId(null);
        assertThat(risposta1).isNotEqualTo(risposta2);
    }
}
