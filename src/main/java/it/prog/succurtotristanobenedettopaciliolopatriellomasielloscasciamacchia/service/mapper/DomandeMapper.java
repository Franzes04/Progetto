package it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.mapper;

import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.domain.Domande;
import it.prog.succurtotristanobenedettopaciliolopatriellomasielloscasciamacchia.service.dto.DomandeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Domande} and its DTO {@link DomandeDTO}.
 */
@Mapper(componentModel = "spring")
public interface DomandeMapper extends EntityMapper<DomandeDTO, Domande> {}
