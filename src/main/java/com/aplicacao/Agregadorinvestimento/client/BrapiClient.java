package com.aplicacao.Agregadorinvestimento.client;

import com.aplicacao.Agregadorinvestimento.client.dto.BrapiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "brapi", url = "https://brapi.dev/")
public interface BrapiClient {

    @GetMapping(value = "/api/quote/{stockId}")
    BrapiResponseDTO getquote(@RequestParam String token, @PathVariable String stockId);

}
