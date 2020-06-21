package com.ltusso.trader.service

import java.math.BigDecimal

class PurchaseInformation(
        val cryptoId: String,
        val cryptoName: String,
        val purchasedAmount: BigDecimal,
        val cryptoPrice: BigDecimal,
        val customerId: Long
)