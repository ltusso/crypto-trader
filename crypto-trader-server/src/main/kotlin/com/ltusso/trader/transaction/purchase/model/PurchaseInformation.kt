package com.ltusso.trader.transaction.purchase.model

import java.math.BigDecimal

class PurchaseInformation(
        val cryptoId: String,
        val cryptoName: String,
        val purchasedAmount: BigDecimal,
        val customerId: Long
)