package com.ltusso.trader.integration.coingecko

import com.ltusso.trader.integration.ProviderDataLoader
import com.ltusso.trader.service.CryptoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CoingeckoDataLoader(@Autowired val coingeckoApi: CoingeckoApi,
                          @Autowired val crypoService: CryptoService) : ProviderDataLoader {

    override fun load() {
        val cryptos = coingeckoApi.getCryptoList()
        if (cryptos != null) {
            for (crypto in cryptos.stream()) {
                crypoService.addCrypto(crypto.id, crypto.currentPrice, crypto.name)
            }
        }

    }

}