package com.ltusso.trader.transaction.sale.service

import com.ltusso.trader.coins.service.CoinService
import com.ltusso.trader.customer.model.Customer
import com.ltusso.trader.customer.service.CustomerService
import com.ltusso.trader.transaction.purchase.service.PurchaseService
import com.ltusso.trader.transaction.sale.model.Sale
import com.ltusso.trader.transaction.sale.model.SaleInformation
import com.ltusso.trader.transaction.sale.repository.SaleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import javax.transaction.Transactional

@Service
class SaleService(@Autowired val coinService: CoinService,
                  @Autowired val customerService: CustomerService,
                  @Autowired val saleRepository: SaleRepository,
                  @Autowired val purchaseService: PurchaseService) {

    @Synchronized
    @Transactional
    fun sell(saleInformation: SaleInformation) {
        val crypto = coinService.getCryptoById(saleInformation.cryptoId)
                ?: throw RuntimeException("crypto ${saleInformation.cryptoId} not found")
        val customer = customerService.findById(saleInformation.customerId)
                ?: throw RuntimeException("customer ${saleInformation.customerId} does not exist")
        val sumarizedPurchases = purchaseService.getSumarizedPurchasesByCustomer(customerId = customer.id)
        val sumarizedPurchase = sumarizedPurchases.find { it.getCoincode().equals(crypto.code) }
                ?: throw RuntimeException("customer ${saleInformation.customerId} does not have any crypto ${saleInformation.cryptoId}")
        if (sumarizedPurchase.getAmount() < saleInformation.amount) {
            throw RuntimeException("customer ${saleInformation.customerId} doesn't have desired amount to sell")
        }
        val sale = Sale(customer = customer, coin = crypto, amount = saleInformation.amount, price = calculatePrice(saleInformation.amount, crypto.price))
        customer.budget = customer.increaseBudget(sale.price)
        saleRepository.save(sale)
        customerService.save(customer)
    }

    fun getSalesByCustomer(customerId: Long): List<Sale> {
        val purchases = purchaseService.getSumarizedPurchasesByCustomer(customerId = 1)
        return saleRepository.findByCustomerId(customerId);
    }

    fun Customer.increaseBudget(increase: BigDecimal): BigDecimal = budget.add(increase)

    private fun calculatePrice(amountToSell: BigDecimal, price: BigDecimal): BigDecimal {
        return price.multiply(amountToSell).setScale(2, RoundingMode.HALF_UP)
    }

}