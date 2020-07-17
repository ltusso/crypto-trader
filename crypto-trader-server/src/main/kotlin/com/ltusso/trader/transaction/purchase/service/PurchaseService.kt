package com.ltusso.trader.transaction.purchase.service

import com.ltusso.trader.coins.model.Coin
import com.ltusso.trader.coins.service.CoinService
import com.ltusso.trader.customer.model.Customer
import com.ltusso.trader.customer.model.CustomerAsset
import com.ltusso.trader.customer.service.CustomerService
import com.ltusso.trader.transaction.purchase.model.Purchase
import com.ltusso.trader.transaction.purchase.model.PurchaseInformation
import com.ltusso.trader.transaction.purchase.repository.PurchaseRepository
import com.ltusso.trader.transaction.purchase.repository.SumarizedPurchase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import javax.transaction.Transactional

@Service
class PurchaseService(@Autowired val coinService: CoinService,
                      @Autowired val customerService: CustomerService,
                      @Autowired val purchaseRepository: PurchaseRepository) {

    @Synchronized
    @Transactional
    fun purchase(purchaseInformation: PurchaseInformation) {
        val crypto = coinService.getCryptoById(purchaseInformation.cryptoId)
                ?: throw RuntimeException("crypto ${purchaseInformation.cryptoId} not found")
        val customer = customerService.findById(purchaseInformation.customerId)
                ?: throw RuntimeException("customer ${purchaseInformation.customerId} does not exist")
        val totalPrice = calculatePrice(purchaseInformation.purchasedAmount, crypto.price)
        if (!customer.hasEnoughMoney(totalPrice)) {
            throw RuntimeException("Not enough money to buy crypto")
        }
        val purchase = Purchase(customer = customer, coin = crypto, price = totalPrice, amount = purchaseInformation.purchasedAmount)
        customer.budget = customer.decreaseBudget(purchase.price)

        buyAsset(customer,crypto,purchaseInformation.purchasedAmount)
        customerService.save(customer)
        purchaseRepository.save(purchase)
    }

    fun buyAsset(customer: Customer, coin: Coin, amount: BigDecimal) {
        var asset = customer.assets?.filter { it -> it.coin.id == coin.id }
        if (asset.isNotEmpty()) {
            var existingAsset = asset.first()
            existingAsset.amount = existingAsset.amount.add(amount)
        } else {
            customer.assets = arrayListOf(CustomerAsset(coin = coin, customer = customer, amount = amount))
        }
    }

    fun getPurchasesByCustomer(customerId: Long): List<Purchase> {
        return purchaseRepository.findByCustomerId(customerId);
    }

    fun Customer.hasEnoughMoney(amount: BigDecimal): Boolean = budget >= amount
    fun Customer.decreaseBudget(decrease: BigDecimal): BigDecimal = budget.minus(decrease)

    private fun calculatePrice(amountToBuy: BigDecimal, price: BigDecimal): BigDecimal {
        return price.multiply(amountToBuy).setScale(2, RoundingMode.HALF_UP)
    }

    fun getSumarizedPurchasesByCustomer(customerId: Long): List<SumarizedPurchase> {
        return purchaseRepository.findSumarizedByCustomerId(customerId)
    }

}