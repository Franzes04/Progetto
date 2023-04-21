package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Risposta} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RispostaDTO implements Serializable {

    private Long id;

    private String risposte;

    private DomandeDTO domande;

    private PersonaDTO persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRisposte() {
        return risposte;
    }

    public void setRisposte(String risposte) {
        this.risposte = risposte;
    }

    public DomandeDTO getDomande() {
        return domande;
    }

    public void setDomande(DomandeDTO domande) {
        this.domande = domande;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RispostaDTO)) {
            return false;
        }

        RispostaDTO rispostaDTO = (RispostaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rispostaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RispostaDTO{" +
            "id=" + getId() +
            ", risposte='" + getRisposte() + "'" +
            ", domande=" + getDomande() +
            ", persona=" + getPersona() +
            "}";
    }
}
