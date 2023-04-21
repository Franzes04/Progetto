package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Domande.
 */
@Entity
@Table(name = "domande")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Domande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Domande id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public Domande descrizione(String descrizione) {
        this.setDescrizione(descrizione);
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domande)) {
            return false;
        }
        return id != null && id.equals(((Domande) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Domande{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
