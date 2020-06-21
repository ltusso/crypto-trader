package com.ltusso.trader.service

import com.ltusso.trader.web.dto.PurchaseDTO
import java.math.BigDecimal

class PurchaseInformation(
        val cryptoId: String,
        val cryptoName: String,
        val purchasedAmount: BigDecimal,
        val cryptoPrice: BigDecimal,
        val customerId: Long
) {
    companion object {
        fun from(purchase: PurchaseDTO): PurchaseInformation = PurchaseInformation(
                purchase.cryptoDTO.id,
                purchase.cryptoDTO.name,
                purchase.amountToBuy,
                purchase.priceUsd,
                purchase.customerId)
    }
}