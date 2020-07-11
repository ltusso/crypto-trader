package com.ltusso.trader.customer.web

import com.ltusso.trader.coins.web.dto.CryptoDTO
import com.ltusso.trader.customer.model.Customer
import com.ltusso.trader.customer.model.CustomerAsset
import com.ltusso.trader.customer.service.CustomerService
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
            return ResponseEntity.ok(CustomerDTO(customer.name, customer.lastName, customer.budget,customer.assets?.map { it-> customer.assetToDto(it) }))
        }
        return ResponseEntity.badRequest().build()
    }

    //Mappers
    fun Customer.assetToDto(asset : CustomerAsset):CustomerAssetDTO{
        return CustomerAssetDTO(asset.coin.code,asset.amount)
    }


}