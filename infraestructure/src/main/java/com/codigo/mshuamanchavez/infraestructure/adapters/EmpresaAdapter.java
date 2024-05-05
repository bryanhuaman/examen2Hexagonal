package com.codigo.mshuamanchavez.infraestructure.adapters;

import com.codigo.mshuamanchavez.domain.aggregates.constants.Constant;
import com.codigo.mshuamanchavez.domain.aggregates.dto.EmpresaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.PersonaDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.SunatDto;
import com.codigo.mshuamanchavez.domain.aggregates.request.EmpresaRequest;
import com.codigo.mshuamanchavez.domain.ports.out.EmpresaServiceOut;
import com.codigo.mshuamanchavez.infraestructure.clientes.ClienteReniec;
import com.codigo.mshuamanchavez.infraestructure.clientes.ClienteSunat;
import com.codigo.mshuamanchavez.infraestructure.dao.EmpresaRepository;
import com.codigo.mshuamanchavez.infraestructure.entity.EmpresaEntity;
import com.codigo.mshuamanchavez.infraestructure.mapper.EmpresaMapper;
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
public class EmpresaAdapter implements EmpresaServiceOut{

    private final EmpresaRepository empresaRepository;
    private final ClienteSunat clienteSunat;
    private final RedisService redisService;

    @Value("${token.reniec}")
    private String tokenSunat;

    @Override
    public EmpresaDto crearEmpresaOut(EmpresaRequest empresaRequest) {
        EmpresaEntity empresaEntity = getEntity(empresaRequest,Constant.CREAR, null);
        return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
    }


    private EmpresaEntity getEntity(EmpresaRequest empresaRequest, int operacion, Long id){
        SunatDto sunatDto = getExecSunat(empresaRequest.getNumDoc());
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setRazonSocial(sunatDto.getRazonSocial());
        //empresaEntity.setTipoDocumento(sunatDto.getTipoDocumento());
        empresaEntity.setTipoDocumento(empresaRequest.getTipoDoc());
        empresaEntity.setNumeroDocumento(sunatDto.getNumeroDocumento());
        empresaEntity.setCondicion(sunatDto.getCondicion());
        empresaEntity.setDireccion(sunatDto.getDireccion());
        empresaEntity.setDistrito(sunatDto.getDistrito());
        empresaEntity.setProvincia(sunatDto.getProvincia());
        empresaEntity.setDepartamento(sunatDto.getDepartamento());
        empresaEntity.setEsAgenteRetencion(sunatDto.isEsAgenteRetencion());
        empresaEntity.setEstado(Constant.STATUS_ACTIVE);

        switch (operacion){
            case 1:
                empresaEntity.setUsuaCrea(Constant.USU_ADMIN);
                empresaEntity.setDateCreate(getTimestamp());
                break;
            case 2:
                empresaEntity.setId(id);
                empresaEntity.setUsuaModif(Constant.USU_ADMIN);
                empresaEntity.setDateModif(getTimestamp());
                break;
        }
        return empresaEntity;
    }

    private SunatDto getExecSunat(String numDoc){
        String authorization = "Bearer "+tokenSunat;
        return clienteSunat.getInfoSunat(numDoc,authorization);
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }


    @Override
    public Optional<EmpresaDto> buscarEmpresaxIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id);
        if(redisInfo!= null){
            EmpresaDto empresaDto = Util.convertirDesdeString(redisInfo,EmpresaDto.class);
            return Optional.of(empresaDto);
        }else{
             EmpresaDto empresaDto = EmpresaMapper.fromEntity(empresaRepository.findById(id).get());
            String dataForRedis = Util.convertirAString(empresaDto);
            redisService.saveInRedis(Constant.REDIS_KEY_OBTENEREMPRESA+id,dataForRedis,10);
             return Optional.of(empresaDto);
        }
    }

    @Override
    public List<EmpresaDto> buscarEmpresaTodosOut() {
        List<EmpresaDto> empresasDto = new ArrayList<>();
        List<EmpresaEntity> entity = empresaRepository.findAll();

        entity.forEach(empresa -> empresasDto.add(EmpresaMapper.fromEntity(empresa)));

        /*for(EmpresaEntity empresa:entity){
            empresasDto.add(EmpresaMapper.fromEntity(empresa));

        }*/

        return empresasDto;
    }

    @Override
    public EmpresaDto actualizarEmpresaOut(Long id, EmpresaRequest empresaRequest) {
        Optional<EmpresaEntity> empresaBuscada = empresaRepository.findById(id);

        if(empresaBuscada.isPresent()){
            EmpresaEntity empresaEntity = getEntity(empresaRequest, Constant.ACTUALIZAR,id);
            empresaEntity.setUsuaCrea(empresaBuscada.get().getUsuaCrea());
            empresaEntity.setDateCreate(empresaBuscada.get().getDateCreate());
            return EmpresaMapper.fromEntity(empresaRepository.save(empresaEntity));
        }else {
            throw new RuntimeException();
        }

    }

    @Override
    public EmpresaDto deleteEmpresaOut(Long id) {
        Optional<EmpresaEntity> empresaBuscada = empresaRepository.findById(id);
        if(empresaBuscada.isPresent()){
            empresaBuscada.get().setEstado(0);
            empresaBuscada.get().setUsuaDelet(Constant.USU_ADMIN);
            empresaBuscada.get().setDateDelet(getTimestamp());
            return EmpresaMapper.fromEntity(empresaRepository.save(empresaBuscada.get()));
        }else{
            throw new RuntimeException();

        }
    }
}
