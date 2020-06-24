package com.ltusso.trader.web.dto

import java.math.BigDecimal

class CustomerDTO(
        val name: String,
        val lastName: String,
        val budget: BigDecimal,
        val purchases: List<PurchaseInfoDTO>) {

    class PurchaseInfoDTO(val cryptoDTO: CryptoDTO,
                          val amount: BigDecimal,
                          val totalPrice: BigDecimal)

}