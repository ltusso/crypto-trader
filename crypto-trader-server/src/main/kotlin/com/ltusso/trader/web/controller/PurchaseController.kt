package com.ltusso.trader.web.controller

import com.ltusso.trader.service.PurchaseService
import com.ltusso.trader.web.dto.PurchaseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/purchase")
class PurchaseController(@Autowired val purchaseService: PurchaseService) {


    @PostMapping
    fun purchaseCrypto(@RequestBody purchase: PurchaseDTO) = purchaseService.purchase(purchase)

}