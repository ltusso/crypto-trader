package com.ltusso.trader.service

import com.ltusso.trader.model.Customer
import com.ltusso.trader.model.Purchase
import com.ltusso.trader.repository.PurchaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import javax.transaction.Transactional

@Service
class PurchaseService(@Autowired val cryptoService: CryptoService,
                      @Autowired val customerService: CustomerService,
                      @Autowired val purchaseRepository: PurchaseRepository) {

    @Synchronized
    @Transactional
    fun purchase(purchaseInformation: PurchaseInformation) {
        val crypto = cryptoService.getCryptoById(purchaseInformation.cryptoId)
                ?: throw RuntimeException("crypto ${purchaseInformation.cryptoId} not found")
        val customer = customerService.findById(purchaseInformation.customerId)
                ?: throw RuntimeException("customer ${purchaseInformation.customerId} does not exist")
        val totalPrice = calculatePrice(purchaseInformation.purchasedAmount, purchaseInformation.cryptoPrice)
        if (!customer.hasEnoughMoney(totalPrice)) {
            throw RuntimeException("Not enough money to buy crypto")
        }
        val purchase = Purchase(customer = customer, crypto = crypto, price = totalPrice, purchasedAmount = purchaseInformation.purchasedAmount)
        customer.budget = customer.decreaseBudget(purchase.price)
        customerService.save(customer)
        purchaseRepository.save(purchase)
    }

    fun getPurchasesByCustomer(customerId : Long):List<Purchase>{
        return purchaseRepository.findByCustomerId(customerId);
    }

    fun Customer.hasEnoughMoney(amount: BigDecimal): Boolean = budget >= amount
    fun Customer.decreaseBudget(decrease: BigDecimal): BigDecimal = budget.minus(decrease)

    private fun calculatePrice(amountToBuy: BigDecimal, price: BigDecimal): BigDecimal {
        return price.multiply(amountToBuy).setScale(2, RoundingMode.HALF_UP)
    }

}