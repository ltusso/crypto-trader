package com.ltusso.trader.coins.web.controller

import com.ltusso.trader.coins.service.CoinService
import com.ltusso.trader.coins.web.dto.CryptoDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/coin")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class CoinController(
        @Autowired
        private val service: CoinService
) {

    @RequestMapping
    fun getAllCryptos(): List<CryptoDTO> {
        return service.getCryptos().stream()
                .map { CryptoDTO(it.name, it.code, it.price, it.variation) }
                .collect(Collectors.toList())
    }

}