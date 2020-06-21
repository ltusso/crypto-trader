package com.ltusso.trader.web.dto

import java.math.BigDecimal

class CustomerDTO(
        val name: String,
        val lastName: String,
        val budget: BigDecimal,
        val cryptoInfo: List<CryptoInfoDTO>) {

    class CryptoInfoDTO(val cryptoDTO: CryptoDTO,
                        val amount: BigDecimal,
                        val totalPrice: BigDecimal)

}