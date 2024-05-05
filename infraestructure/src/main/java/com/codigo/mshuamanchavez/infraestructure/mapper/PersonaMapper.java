package com.codigo.mshuamanchavez.infraestructure.mapper;

import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.infraestructure.entity.EmpresaEntity;
import com.codigo.mshuamanchavez.infraestructure.entity.PersonaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {
    public static PersonaDto fromEntity(PersonaEntity personaEntity){
        PersonaDto personaDto = new PersonaDto();
        personaDto.setId(personaEntity.getId());
        personaDto.setNombre(personaEntity.getNombre());
        personaDto.setApellido(personaEntity.getApellido());
        personaDto.setTipoDocumento(personaEntity.getTipoDocumento());
        personaDto.setNumeroDocumento(personaEntity.getNumeroDocumento());
        personaDto.setEmail(personaEntity.getEmail());
        personaDto.setTelefono(personaEntity.getTelefono());
        personaDto.setDireccion(personaEntity.getDireccion());
        personaDto.setEstado(personaEntity.getEstado());
        personaDto.setUsuaCrea(personaEntity.getUsuaCrea());
        personaDto.setDateCreate(personaEntity.getDateCreate());
        personaDto.setUsuaModif(personaEntity.getUsuaModif());
        personaDto.setDateModif(personaEntity.getDateModif());
        personaDto.setUsuaDelet(personaEntity.getUsuaDelet());
        personaDto.setDateDelet(personaEntity.getDateDelet());
        personaDto.setEmpresa(EmpresaMapper.fromEntity(personaEntity.getEmpresa()));
        return personaDto;
    }


}
