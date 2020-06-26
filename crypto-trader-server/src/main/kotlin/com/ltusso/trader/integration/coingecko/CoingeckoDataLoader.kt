package com.ltusso.trader.integration.coingecko

import com.ltusso.trader.integration.ProviderDataLoader
import com.ltusso.trader.service.CryptoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class CoingeckoDataLoader(@Autowired val coingeckoApi: CoingeckoApi,
                          @Autowired val crypoService: CryptoService) : ProviderDataLoader {

    override fun load() {
        val cryptos = coingeckoApi.getCryptoList()
        for (crypto in cryptos.stream()) {
            crypoService.addCrypto(crypto.id, crypto.currentPrice, crypto.name)
        }

    }

    @Scheduled(fixedDelay = 10000)
    override fun synchronize() {
        val cryptos = coingeckoApi.getCryptoList()
        for (crypto in cryptos.stream()) {
            crypoService.updateCrypto(crypto.id,crypto.currentPrice)
        }

    }

}