package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Persona;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.PersonaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Persona} and its DTO {@link PersonaDTO}.
 */
@Mapper(componentModel = "spring")
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {}
