package com.ltusso.trader.integration.coingecko

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CoingeckoCrypto(
        val id: String,
        val symbol: String,
        val name: String,
        @JsonProperty("current_price")
        val currentPrice: BigDecimal
)