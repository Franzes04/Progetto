package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DomandeDTO implements Serializable {

    private Long id;

    private String descrizione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomandeDTO)) {
            return false;
        }

        DomandeDTO domandeDTO = (DomandeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, domandeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DomandeDTO{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
