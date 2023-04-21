package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain;

import static org.assertj.core.api.Assertions.assertThat;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DomandeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Domande.class);
        Domande domande1 = new Domande();
        domande1.setId(1L);
        Domande domande2 = new Domande();
        domande2.setId(domande1.getId());
        assertThat(domande1).isEqualTo(domande2);
        domande2.setId(2L);
        assertThat(domande1).isNotEqualTo(domande2);
        domande1.setId(null);
        assertThat(domande1).isNotEqualTo(domande2);
    }
}
