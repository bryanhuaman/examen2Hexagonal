package com.codigo.mshuamanchavez.domain.ports.out;

import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonaDto crearPersonaOut(PersonaRequest personaRequest);
    Optional<PersonaDto> buscarxIdOut(Long id);
    List<PersonaDto> buscarTodosOut();
    PersonaDto actualizarPersonaOut(Long id, PersonaRequest personaRequest);
    PersonaDto deletePersonaOut(Long id);
}
