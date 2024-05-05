package com.codigo.mshuamanchavez.domain.impl;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;
import com.codigo.mshuamanchavez.domain.ports.in.EmpresaServiceIn;
import com.codigo.mshuamanchavez.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;

    @Override
    public EmpresaDto crearEmpresaIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearEmpresaOut(empresaRequest);
    }

    @Override
    public Optional<EmpresaDto> buscarEmpresaxIdIn(Long id) {
        return empresaServiceOut.buscarEmpresaxIdOut(id);
    }

    @Override
    public List<EmpresaDto> buscarEmpresaTodosIn() {
        return empresaServiceOut.buscarEmpresaTodosOut();
    }

    @Override
    public EmpresaDto actualizarEmpresaIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarEmpresaOut(id,empresaRequest);
    }

    @Override
    public EmpresaDto deleteEmpresaIn(Long id) {
        return empresaServiceOut.deleteEmpresaOut(id);
    }
}
