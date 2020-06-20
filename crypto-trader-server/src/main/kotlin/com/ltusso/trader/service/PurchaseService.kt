package com.ltusso.trader.service

import com.ltusso.trader.model.Crypto
import com.ltusso.trader.model.Customer
import com.ltusso.trader.model.Purchase
import com.ltusso.trader.repository.PurchaseRepository
import com.ltusso.trader.web.dto.PurchaseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import javax.transaction.Transactional

fun Customer.enoughMoney(amount: BigDecimal): Boolean = budget >= amount
fun Customer.decreaseBudget(decrease: BigDecimal): BigDecimal = budget.minus(decrease)

@Service
class PurchaseService(@Autowired val cryptoService: CryptoService,
                      @Autowired val customerService: CustomerService,
                      @Autowired val purchaseRepository: PurchaseRepository
) {


    @Synchronized
    @Transactional
    fun purchase(dto: PurchaseDTO) {
        val crypto = cryptoService.getCryptoByCode(dto.cryptoCode)
        val customer = customerService.findById(dto.customerId).orElseThrow { RuntimeException("customer ${dto.customerId} does not exist") }
        if (!customer.enoughMoney(dto.amountToBuy)) {
            throw RuntimeException("Not enough money to buy crypto")
        }

        val purchase = Purchase(customer = customer, crypto = crypto, price = calculatePrice(dto.amountToBuy, crypto), purchasedAmount = dto.amountToBuy)
        customer.budget = customer.decreaseBudget(purchase.price)
        customerService.save(customer)
        purchaseRepository.save(purchase)
    }

    private fun calculatePrice(amountToBuy: BigDecimal, crypto: Crypto): BigDecimal = crypto.price.multiply(amountToBuy).setScale(2, RoundingMode.HALF_UP)

}