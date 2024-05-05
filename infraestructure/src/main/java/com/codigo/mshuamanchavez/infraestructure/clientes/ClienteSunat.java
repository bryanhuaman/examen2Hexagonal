package com.codigo.mshuamanchavez.infraestructure.clientes;

import com.codigo.mshuamanchavez.domain.aggregates.dto.ReniecDto;
import com.codigo.mshuamanchavez.domain.aggregates.dto.SunatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-sunat", url = "https://api.apis.net.pe/v2/sunat/")
public interface ClienteSunat {

    @GetMapping("/ruc")
    SunatDto getInfoSunat(@RequestParam("numero") String numero, @RequestHeader("Authorization") String authorization);

}
