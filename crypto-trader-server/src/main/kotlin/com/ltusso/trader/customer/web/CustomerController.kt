package com.ltusso.trader.web.controller

import com.ltusso.trader.service.CustomerService
import com.ltusso.trader.web.dto.CustomerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class CustomerController(
        @Autowired val customerService: CustomerService
) {

    @RequestMapping
    fun findAll(): ResponseEntity<List<CustomerDTO>> {
        return ResponseEntity.ok(customerService.findAll()
                .map { customer ->
                    CustomerDTO(customer.name, customer.lastName, customer.budget)
                }
        )
    }

    @RequestMapping("/{customerId}")
    fun find(@PathVariable customerId: Long): ResponseEntity<CustomerDTO> {
        val customer = customerService.findById(customerId)

        if (customer != null) {
            return ResponseEntity.ok(CustomerDTO(customer.name, customer.lastName, customer.budget))
        }
        return ResponseEntity.badRequest().build();
    }


}