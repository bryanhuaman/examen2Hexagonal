package com.codigo.mshuamanchavez.applicaction.controller;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;
import com.codigo.mshuamanchavez.domain.ports.in.EmpresaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empresa")
@AllArgsConstructor
public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;

    @PostMapping("/crearEmpresa")
    public ResponseEntity<EmpresaDto> crearEmpresa(@RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearEmpresaIn(empresaRequest));
    }

    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<Optional<EmpresaDto>> obtenerEmpresaxId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.buscarEmpresaxIdIn(id));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<EmpresaDto>> obtenerEmpresaTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.buscarEmpresaTodosIn());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EmpresaDto> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.actualizarEmpresaIn(id, empresaRequest));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<EmpresaDto> deleteEmpresa(@PathVariable Long id ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empresaServiceIn.deleteEmpresaIn(id));
    }



}
