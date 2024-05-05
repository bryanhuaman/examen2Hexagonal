package com.codigo.mshuamanchavez.infraestructure.mapper;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.infraestructure.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {

    public static EmpresaDto fromEntity(EmpresaEntity empresaEntity){

        EmpresaDto empresaDto = EmpresaDto.builder()
                .id(empresaEntity.getId())
                .razonSocial(empresaEntity.getRazonSocial())
                .tipoDocumento(empresaEntity.getTipoDocumento())
                .numeroDocumento(empresaEntity.getNumeroDocumento())
                .estado(empresaEntity.getEstado())
                .condicion(empresaEntity.getCondicion())
                .direccion(empresaEntity.getDireccion())
                .distrito(empresaEntity.getDistrito())
                .provincia(empresaEntity.getProvincia())
                .departamento(empresaEntity.getDepartamento())
                .esAgenteRetencion(empresaEntity.isEsAgenteRetencion())
                .usuaCrea(empresaEntity.getUsuaCrea())
                .dateCreate(empresaEntity.getDateCreate())
                .usuaModif(empresaEntity.getUsuaModif())
                .dateModif(empresaEntity.getDateModif())
                .usuaDelet(empresaEntity.getUsuaDelet())
                .dateDelet(empresaEntity.getDateDelet()).build();

        return empresaDto;
    }

}
