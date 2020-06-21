package com.ltusso.trader.web.controller

import com.ltusso.trader.service.CustomerService
import com.ltusso.trader.web.dto.CryptoDTO
import com.ltusso.trader.web.dto.CustomerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
        @Autowired val customerService: CustomerService
) {

    @RequestMapping
    fun findAll(): List<CustomerDTO> {
        return customerService.findAll()
                .map { customer ->
                    val cryptoInfoPurchases = customer.purchases
                            .map { CustomerDTO.CryptoInfoDTO(
                                    cryptoDTO = CryptoDTO(it.crypto.name, it.crypto.code),
                                    amount = it.purchasedAmount,
                                    totalPrice = it.price)
                            }
                    CustomerDTO(customer.name, customer.lastName, customer.budget, cryptoInfoPurchases)
                }
    }
}