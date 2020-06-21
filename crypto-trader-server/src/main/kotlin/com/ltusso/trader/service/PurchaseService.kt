package com.ltusso.trader.service

import com.ltusso.trader.model.Crypto
import com.ltusso.trader.model.Customer
import com.ltusso.trader.model.Purchase
import com.ltusso.trader.repository.PurchaseRepository
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
    fun purchase(purchaseInformation: PurchaseInformation) {
        val crypto = cryptoService.getCryptoById(purchaseInformation.cryptoId)
                .orElse(cryptoService.addCrypto(Crypto(name = purchaseInformation.cryptoName, code = purchaseInformation.cryptoId)))
        val customer = customerService.findById(purchaseInformation.customerId).orElseThrow { RuntimeException("customer ${purchaseInformation.customerId} does not exist") }

        val totalPrice = calculatePrice(purchaseInformation.purchasedAmount, purchaseInformation.cryptoPrice)
        if (!customer.enoughMoney(totalPrice)) {
            throw RuntimeException("Not enough money to buy crypto")
        }

        val purchase = Purchase(customer = customer, crypto = crypto, price = totalPrice, purchasedAmount = purchaseInformation.purchasedAmount)
        customer.budget = customer.decreaseBudget(purchase.price)
        customerService.save(customer)
        purchaseRepository.save(purchase)
    }

    private fun calculatePrice(amountToBuy: BigDecimal, price: BigDecimal): BigDecimal = price?.multiply(amountToBuy).setScale(2, RoundingMode.HALF_UP)

}