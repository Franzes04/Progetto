package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Persona.
 */
@Entity
@Table(name = "persona")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Column(name = "email")
    private String email;

    @Column(name = "citta")
    private String citta;

    @Column(name = "eta")
    private Integer eta;

    @Column(name = "telefono")
    private String telefono;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Persona id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public Persona nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public Persona cognome(String cognome) {
        this.setCognome(cognome);
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return this.dataNascita;
    }

    public Persona dataNascita(LocalDate dataNascita) {
        this.setDataNascita(dataNascita);
        return this;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return this.email;
    }

    public Persona email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitta() {
        return this.citta;
    }

    public Persona citta(String citta) {
        this.setCitta(citta);
        return this;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getEta() {
        return this.eta;
    }

    public Persona eta(Integer eta) {
        this.setEta(eta);
        return this;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Persona telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persona)) {
            return false;
        }
        return id != null && id.equals(((Persona) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Persona{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", dataNascita='" + getDataNascita() + "'" +
            ", email='" + getEmail() + "'" +
            ", citta='" + getCitta() + "'" +
            ", eta=" + getEta() +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
}
