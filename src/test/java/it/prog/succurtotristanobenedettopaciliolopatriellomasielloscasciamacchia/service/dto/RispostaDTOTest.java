package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RispostaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RispostaDTO.class);
        RispostaDTO rispostaDTO1 = new RispostaDTO();
        rispostaDTO1.setId(1L);
        RispostaDTO rispostaDTO2 = new RispostaDTO();
        assertThat(rispostaDTO1).isNotEqualTo(rispostaDTO2);
        rispostaDTO2.setId(rispostaDTO1.getId());
        assertThat(rispostaDTO1).isEqualTo(rispostaDTO2);
        rispostaDTO2.setId(2L);
        assertThat(rispostaDTO1).isNotEqualTo(rispostaDTO2);
        rispostaDTO1.setId(null);
        assertThat(rispostaDTO1).isNotEqualTo(rispostaDTO2);
    }
}
