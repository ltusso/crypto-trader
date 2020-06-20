package com.ltusso.trader.web.controller

import com.ltusso.trader.service.CryptoService
import com.ltusso.trader.web.dto.CryptoDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/crypto")
class CryptoController(
        @Autowired
        private val service: CryptoService
) {

    @RequestMapping
    fun getAllCryptos(): List<CryptoDTO> = service.getCryptos().stream().map { CryptoDTO.from(it) }.collect(Collectors.toList())

    @PostMapping
    fun addCrypto(@RequestBody dto: CryptoDTO) = service.addCrypto(dto)


}