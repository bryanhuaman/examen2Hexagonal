package com.codigo.mshuamanchavez.infraestructure.adapters;

import com.codigo.mshuamanchavez.domain.aggregates.constants.Constant;
import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.ReniecDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.SunatDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;
import com.codigo.mshuamanchavez.domain.aggregates.request.PersonaRequest;
import com.codigo.mshuamanchavez.domain.ports.out.PersonaServiceOut;
import com.codigo.mshuamanchavez.infraestructure.clientes.ClienteReniec;
import com.codigo.mshuamanchavez.infraestructure.clientes.ClienteSunat;
import com.codigo.mshuamanchavez.infraestructure.dao.EmpresaRepository;
import com.codigo.mshuamanchavez.infraestructure.dao.PersonaRepository;
import com.codigo.mshuamanchavez.infraestructure.entity.EmpresaEntity;
import com.codigo.mshuamanchavez.infraestructure.entity.PersonaEntity;
import com.codigo.mshuamanchavez.infraestructure.mapper.EmpresaMapper;
import com.codigo.mshuamanchavez.infraestructure.mapper.PersonaMapper;
import com.codigo.mshuamanchavez.infraestructure.redis.RedisService;
import com.codigo.mshuamanchavez.infraestructure.util.Util;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final EmpresaRepository empresaRepository;
    private final ClienteReniec clienteReniec;
    private final RedisService redisService;

    @Value("${token.reniec}")
    private String tokenReniec;
    @Override
    public PersonaDto crearPersonaOut(PersonaRequest personaRequest) {
        Optional<EmpresaEntity> empresaEntity = empresaRepository.findByNumeroDocumento(personaRequest.getEmpresa());
        if(empresaEntity.isPresent()){
            PersonaEntity personaEntity = getEntity(personaRequest,Constant.CREAR, null);
            personaEntity.setEmpresa(empresaEntity.get());
            return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
        }else {
            throw new RuntimeException();
        }

    }

    private PersonaEntity getEntity(PersonaRequest personaRequest, int operacion, Long id){
        ReniecDto reniecDto = getExecReniec(personaRequest.getNumDoc());
        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setNombre(reniecDto.getNombres());
        personaEntity.setApellido( reniecDto.getApellidoPaterno() + " " + reniecDto.getApellidoMaterno());
        personaEntity.setEmail(personaRequest.getEmail());
        personaEntity.setTelefono(personaRequest.getTelefono());
        personaEntity.setDireccion(personaRequest.getDireccion());
        personaEntity.setTipoDocumento(personaRequest.getTipoDoc());
        personaEntity.setNumeroDocumento(reniecDto.getNumeroDocumento());
        personaEntity.setEstado(Constant.STATUS_ACTIVE);

        switch (operacion){
            case 1:
                personaEntity.setUsuaCrea(Constant.USU_ADMIN);
                personaEntity.setDateCreate(getTimestamp());
                break;
            case 2:
                personaEntity.setId(id);
                personaEntity.setUsuaModif(Constant.USU_ADMIN);
                personaEntity.setDateModif(getTimestamp());
                break;
        }
        return personaEntity;
    }

    private ReniecDto getExecReniec(String numDoc){
        String authorization = "Bearer "+tokenReniec;
        return clienteReniec.getInfoReniec(numDoc,authorization);
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }



    @Override
    public Optional<PersonaDto> buscarxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENERPERSONA+id);
        if(redisInfo!= null){
            PersonaDto personaDto = Util.convertirDesdeString(redisInfo,PersonaDto.class);
            return Optional.of(personaDto);
        }else{
            PersonaDto personaDto = PersonaMapper.fromEntity(personaRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(personaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENERPERSONA+id,dataForRedis,10);
            return Optional.of(personaDto);
        }
    }

    @Override
    public List<PersonaDto> buscarTodosOut() {
        List<PersonaDto> personaDto = new ArrayList<>();
        List<PersonaEntity> entity = personaRepository.findAll();
        for(PersonaEntity empresa:entity){
            personaDto.add(PersonaMapper.fromEntity(empresa));

        }
        return personaDto;
    }

    @Override
    public PersonaDto actualizarPersonaOut(Long id, PersonaRequest personaRequest) {
        Optional<PersonaEntity> personaBuscada = personaRepository.findById(id);
        Optional<EmpresaEntity> empresaEntity = empresaRepository.findByNumeroDocumento(personaRequest.getEmpresa());
        if(personaBuscada.isPresent()){
            PersonaEntity personaEntity = getEntity(personaRequest,Constant.ACTUALIZAR,id);
            personaEntity.setEmpresa(empresaEntity.get());
            personaEntity.setUsuaCrea(personaBuscada.get().getUsuaCrea());
            personaEntity.setDateCreate(personaBuscada.get().getDateCreate());
            return PersonaMapper.fromEntity(personaRepository.save(personaEntity));
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public PersonaDto deletePersonaOut(Long id) {
        Optional<PersonaEntity> personaBuscada = personaRepository.findById(id);
        if(personaBuscada.isPresent()){
            personaBuscada.get().setEstado(0);
            personaBuscada.get().setUsuaDelet(Constant.USU_ADMIN);
            personaBuscada.get().setDateDelet(getTimestamp());
            return PersonaMapper.fromEntity(personaRepository.save(personaBuscada.get()));
        }else{
            throw new RuntimeException();

        }
    }
}
