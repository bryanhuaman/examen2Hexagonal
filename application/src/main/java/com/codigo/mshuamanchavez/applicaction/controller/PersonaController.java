package com.codigo.mshuamanchavez.applicaction.controller;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;
import com.codigo.mshuamanchavez.domain.aggregates.request.PersonaRequest;
import com.codigo.mshuamanchavez.domain.ports.in.PersonaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/persona")
@AllArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/crearPersona")
    public ResponseEntity<PersonaDto> crearPersona(@RequestBody PersonaRequest personaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(personaRequest));
    }

    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<Optional<PersonaDto>> obtenerPersonaxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.buscarxIdIn(id));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<PersonaDto>> obtenerPersonaTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.buscarTodosIn());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PersonaDto> actualizarPersona(@PathVariable Long id, @RequestBody PersonaRequest personaRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarPersonaIn(id,personaRequest));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<PersonaDto> deletePersona(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.deletePersonaIn(id));
    }

}
