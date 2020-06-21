package com.ltusso.trader.service

import com.ltusso.trader.model.Customer
import com.ltusso.trader.repository.CustomerRepository
import org.jetbrains.annotations.Nullable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(@Autowired val customerRepository: CustomerRepository) {

    fun findById(id: Long): Customer? {
        return customerRepository.findById(id).orElse(null)
    }

    fun save(customer: Customer) = customerRepository.save(customer)

    fun findAll(): List<Customer> = customerRepository.findAll()

}