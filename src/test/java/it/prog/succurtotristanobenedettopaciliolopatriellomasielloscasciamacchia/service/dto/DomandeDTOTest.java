package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DomandeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DomandeDTO.class);
        DomandeDTO domandeDTO1 = new DomandeDTO();
        domandeDTO1.setId(1L);
        DomandeDTO domandeDTO2 = new DomandeDTO();
        assertThat(domandeDTO1).isNotEqualTo(domandeDTO2);
        domandeDTO2.setId(domandeDTO1.getId());
        assertThat(domandeDTO1).isEqualTo(domandeDTO2);
        domandeDTO2.setId(2L);
        assertThat(domandeDTO1).isNotEqualTo(domandeDTO2);
        domandeDTO1.setId(null);
        assertThat(domandeDTO1).isNotEqualTo(domandeDTO2);
    }
}
