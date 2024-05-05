package com.codigo.mshuamanchavez.domain.ports.out;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {

    EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarEmpresaxIdOut(Long id);
    List<EmpresaDto> buscarEmpresaTodosOut();
    EmpresaDto actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest);
    EmpresaDto deleteEmpresaOut(Long id);
}
