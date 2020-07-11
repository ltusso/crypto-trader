package com.ltusso.trader.customer.service

import com.ltusso.trader.coins.model.Coin
import com.ltusso.trader.customer.model.Customer
import com.ltusso.trader.customer.model.CustomerAsset
import com.ltusso.trader.customer.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CustomerService(@Autowired val customerRepository: CustomerRepository) {

    fun findById(id: Long): Customer? {
        return customerRepository.findById(id).orElse(null)
    }

    fun save(customer: Customer) = customerRepository.save(customer)

    fun findAll(): List<Customer> = customerRepository.findAll()

    fun updateAsset(customer: Customer, coin: Coin, amount: BigDecimal) {
        var asset = customer.assets?.filter { it -> it.coin.id == coin.id }
        if (asset.isNotEmpty()) {
            var existingAsset = asset.first()
            existingAsset.amount = existingAsset.amount.add(amount)
        } else {
            customer.assets = arrayListOf(CustomerAsset(coin = coin, customer = customer, amount = amount))
        }
        customerRepository.saveAndFlush(customer)
    }
}