package com.ltusso.trader.integration.coincap

import com.ltusso.trader.integration.ProviderDataLoader
import com.ltusso.trader.service.CryptoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CoincapDataDataLoader(@Autowired val coincapApi: CoincapApi,
                            @Autowired val crypoService: CryptoService) : ProviderDataLoader {

    override fun load() {
        val cryptos = coincapApi.getCryptoList()
        if (cryptos != null) {
            for (crypto in cryptos.stream()) {
                crypoService.addCrypto(crypto.id, crypto.priceUsd, crypto.name)
            }
        }

    }

}