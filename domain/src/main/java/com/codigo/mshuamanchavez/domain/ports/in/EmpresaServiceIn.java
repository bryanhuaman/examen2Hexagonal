package com.codigo.mshuamanchavez.domain.ports.in;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest);
    Optional<EmpresaDto> buscarEmpresaxIdIn(Long id);
    List<EmpresaDto> buscarEmpresaTodosIn();
    EmpresaDto actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest);
    EmpresaDto deleteEmpresaIn(Long id);
}
