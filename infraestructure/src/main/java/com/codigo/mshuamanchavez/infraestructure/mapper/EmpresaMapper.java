package com.codigo.mshuamanchavez.infraestructure.mapper;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.infraestructure.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaMapper {

    public static EmpresaDto fromEntity(EmpresaEntity empresaEntity){

        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setId(empresaEntity.getId());
        empresaDto.setRazonSocial(empresaEntity.getRazonSocial());
        empresaDto.setTipoDocumento(empresaEntity.getTipoDocumento());
        empresaDto.setNumeroDocumento(empresaEntity.getNumeroDocumento());
        empresaDto.setEstado(empresaEntity.getEstado());
        empresaDto.setCondicion(empresaEntity.getCondicion());
        empresaDto.setDireccion(empresaEntity.getDireccion());
        empresaDto.setDistrito(empresaEntity.getDistrito());
        empresaDto.setProvincia(empresaEntity.getProvincia());
        empresaDto.setDepartamento(empresaEntity.getDepartamento());
        empresaDto.setEsAgenteRetencion(empresaEntity.isEsAgenteRetencion());
        empresaDto.setUsuaCrea(empresaEntity.getUsuaCrea());
        empresaDto.setDateCreate(empresaEntity.getDateCreate());
        empresaDto.setUsuaModif(empresaEntity.getUsuaModif());
        empresaDto.setDateModif(empresaEntity.getDateModif());
        empresaDto.setUsuaDelet(empresaEntity.getUsuaDelet());
        empresaDto.setDateDelet(empresaEntity.getDateDelet());
        return empresaDto;
    }

}
