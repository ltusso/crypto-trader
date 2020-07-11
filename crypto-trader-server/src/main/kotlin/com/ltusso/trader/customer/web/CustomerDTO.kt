package com.ltusso.trader.customer.web

import java.math.BigDecimal

class CustomerDTO(
        val name: String,
        val lastName: String,
        val budget: BigDecimal,
        val assets: List<CustomerAssetDTO>? = emptyList()) {
}