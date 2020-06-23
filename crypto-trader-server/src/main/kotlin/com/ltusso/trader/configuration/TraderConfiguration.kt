package com.ltusso.trader.configuration

import com.ltusso.trader.integration.ProviderDataLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class TraderConfiguration(@Autowired val provider: ProviderDataLoader) {

    @PostConstruct
    fun load() {
        provider.load()
    }
}