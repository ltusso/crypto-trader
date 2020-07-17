package com.ltusso.trader.customer.service

import com.ltusso.trader.customer.model.Customer
import com.ltusso.trader.customer.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService(@Autowired val customerRepository: CustomerRepository) {

    fun findById(id: Long): Customer? {
        return customerRepository.findById(id).orElse(null)
    }

    fun save(customer: Customer) = customerRepository.save(customer)

    fun findAll(): List<Customer> = customerRepository.findAll()
}