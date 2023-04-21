package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Persona;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Risposta;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.DomandeDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.PersonaDTO;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.RispostaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Risposta} and its DTO {@link RispostaDTO}.
 */
@Mapper(componentModel = "spring")
public interface RispostaMapper extends EntityMapper<RispostaDTO, Risposta> {
    @Mapping(target = "domande", source = "domande", qualifiedByName = "domandeDescrizione")
    @Mapping(target = "persona", source = "persona", qualifiedByName = "personaNome")
    RispostaDTO toDto(Risposta s);

    @Named("domandeDescrizione")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "descrizione", source = "descrizione")
    DomandeDTO toDtoDomandeDescrizione(Domande domande);

    @Named("personaNome")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    PersonaDTO toDtoPersonaNome(Persona persona);
}
