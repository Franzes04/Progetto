package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Risposta.
 */
@Entity
@Table(name = "risposta")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Risposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "risposte")
    private String risposte;

    @ManyToOne
    private Domande domande;

    @ManyToOne
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Risposta id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRisposte() {
        return this.risposte;
    }

    public Risposta risposte(String risposte) {
        this.setRisposte(risposte);
        return this;
    }

    public void setRisposte(String risposte) {
        this.risposte = risposte;
    }

    public Domande getDomande() {
        return this.domande;
    }

    public void setDomande(Domande domande) {
        this.domande = domande;
    }

    public Risposta domande(Domande domande) {
        this.setDomande(domande);
        return this;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Risposta persona(Persona persona) {
        this.setPersona(persona);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Risposta)) {
            return false;
        }
        return id != null && id.equals(((Risposta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Risposta{" +
            "id=" + getId() +
            ", risposte='" + getRisposte() + "'" +
            "}";
    }
}
