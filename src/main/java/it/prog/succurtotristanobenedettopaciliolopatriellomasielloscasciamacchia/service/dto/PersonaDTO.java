package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Persona} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PersonaDTO implements Serializable {

    private Long id;

    private String nome;

    private String cognome;

    private LocalDate dataNascita;

    private String email;

    private String citta;

    private Integer eta;

    private String telefono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonaDTO)) {
            return false;
        }

        PersonaDTO personaDTO = (PersonaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, personaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaDTO{" +
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
