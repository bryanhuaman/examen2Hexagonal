package com.codigo.mshuamanchavez.domain.impl;

import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.PersonaRequest;
import com.codigo.mshuamanchavez.domain.ports.in.PersonaServiceIn;
import com.codigo.mshuamanchavez.domain.ports.out.PersonaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {


    private final PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDto crearPersonaIn(PersonaRequest personaRequest) {
        return personaServiceOut.crearPersonaOut(personaRequest);
    }

    @Override
    public Optional<PersonaDto> buscarxIdIn(Long id) {
        return personaServiceOut.buscarxIdOut(id);
    }

    @Override
    public List<PersonaDto> buscarTodosIn() {
        return personaServiceOut.buscarTodosOut();
    }

    @Override
    public PersonaDto actualizarPersonaIn(Long id, PersonaRequest personaRequest) {
        return personaServiceOut.actualizarPersonaOut(id,personaRequest);
    }

    @Override
    public PersonaDto deletePersonaIn(Long id) {
        return personaServiceOut.deletePersonaOut(id);
    }
}
