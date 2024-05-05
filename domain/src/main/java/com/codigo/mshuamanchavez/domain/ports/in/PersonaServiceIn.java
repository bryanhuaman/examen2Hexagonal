package com.codigo.mshuamanchavez.domain.ports.in;

import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {

    PersonaDto crearPersonaIn(PersonaRequest personaRequest);
    Optional<PersonaDto> buscarxIdIn(Long id);
    List<PersonaDto> buscarTodosIn();
    PersonaDto actualizarPersonaIn(Long id, PersonaRequest personaRequest);
    PersonaDto deletePersonaIn(Long id);
}
