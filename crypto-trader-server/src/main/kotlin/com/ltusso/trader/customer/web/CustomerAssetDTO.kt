package com.ltusso.trader.customer.web

import java.math.BigDecimal

data class CustomerAssetDTO(
        val code: String,
        val amount: BigDecimal
)