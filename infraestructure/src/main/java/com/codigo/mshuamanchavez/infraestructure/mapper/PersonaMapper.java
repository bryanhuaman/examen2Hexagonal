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
        PersonaDto personaDto = PersonaDto.builder()
                .id(personaEntity.getId())
                .nombre(personaEntity.getNombre())
                .apellido(personaEntity.getApellido())
                .tipoDocumento(personaEntity.getTipoDocumento())
                .numeroDocumento(personaEntity.getNumeroDocumento())
                .email(personaEntity.getEmail())
                .telefono(personaEntity.getTelefono())
                .direccion(personaEntity.getDireccion())
                .estado(personaEntity.getEstado())
                .usuaCrea(personaEntity.getUsuaCrea())
                .dateCreate(personaEntity.getDateCreate())
                .usuaModif(personaEntity.getUsuaModif())
                .dateModif(personaEntity.getDateModif())
                .usuaDelet(personaEntity.getUsuaDelet())
                .dateDelet(personaEntity.getDateDelet())
                .empresa(EmpresaMapper.fromEntity(personaEntity.getEmpresa())).build();

        return personaDto;
    }


}
