package com.ltusso.trader.web.controller

import com.ltusso.trader.service.CustomerService
import com.ltusso.trader.web.dto.CryptoDTO
import com.ltusso.trader.web.dto.CustomerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
        @Autowired val customerService: CustomerService
) {

    @RequestMapping
    fun findAll(): ResponseEntity<List<CustomerDTO>> {
        return ResponseEntity.ok(customerService.findAll()
                .map { customer ->
                    val cryptoInfoPurchases = customer.purchases
                            .map {
                                CustomerDTO.CryptoInfoDTO(
                                        cryptoDTO = CryptoDTO(it.crypto.name, it.crypto.code, it.crypto.price),
                                        amount = it.purchasedAmount,
                                        totalPrice = it.price)
                            }
                    CustomerDTO(customer.name, customer.lastName, customer.budget, cryptoInfoPurchases)
                }
        )
    }

    @RequestMapping("/{customerId}")
    fun find(@PathVariable customerId: Long): ResponseEntity<CustomerDTO> {
        val customer = customerService.findById(customerId)

        if (customer != null) {
            return ResponseEntity.ok(CustomerDTO(customer.name, customer.lastName, customer.budget, customer.purchases
                    .map {
                        CustomerDTO.CryptoInfoDTO(
                                cryptoDTO = CryptoDTO(it.crypto.name, it.crypto.code, it.price),
                                amount = it.purchasedAmount,
                                totalPrice = it.price)
                    }))
        }
        return ResponseEntity.badRequest().build();
    }


}