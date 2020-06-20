package com.ltusso.trader.web.controller

import com.ltusso.trader.service.CustomerService
import com.ltusso.trader.web.dto.CustomerDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/customer")
class CustomerController(
        @Autowired val customerService: CustomerService
) {

    @RequestMapping
    fun findAll(): List<CustomerDTO> = customerService.findAll().stream().map { CustomerDTO.from(it) }.collect(Collectors.toList())
}