package com.ltusso.trader.transaction.sale.model

import java.math.BigDecimal

class SaleInformation(
        val cryptoId: String,
        val amount: BigDecimal,
        val customerId: Long
)