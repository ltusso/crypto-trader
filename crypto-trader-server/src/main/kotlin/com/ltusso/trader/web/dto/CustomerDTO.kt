package com.ltusso.trader.web.dto

import com.ltusso.trader.model.Customer
import com.ltusso.trader.model.Purchase
import java.math.BigDecimal
import java.util.stream.Collectors

class CustomerDTO(
        val name: String,
        val lastName: String,
        val budget: BigDecimal,
        val cryptoInfo: List<CryptoInfoDTO>) {

    companion object {
        fun from(customer: Customer): CustomerDTO = CustomerDTO(customer.name, customer.lastName, customer.budget, CryptoInfoDTO.from(customer.purchases))
    }

}

class CryptoInfoDTO(val cryptoDTO: CryptoDTO,val amount: BigDecimal,val totalPrice: BigDecimal) {
    companion object {
        fun from(purchases: List<Purchase>): List<CryptoInfoDTO> =
                purchases.stream().map { CryptoInfoDTO(CryptoDTO.from(it.crypto), it.purchasedAmount, it.price) }.collect(Collectors.toList())

    }
}