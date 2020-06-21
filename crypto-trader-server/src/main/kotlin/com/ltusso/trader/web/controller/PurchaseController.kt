package com.ltusso.trader.web.controller

import com.ltusso.trader.service.PurchaseInformation
import com.ltusso.trader.service.PurchaseService
import com.ltusso.trader.web.dto.PurchaseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class PurchaseController(@Autowired val purchaseService: PurchaseService) {


    @PostMapping
    fun purchaseCrypto(@RequestBody purchase: PurchaseDTO) = purchaseService.purchase(PurchaseInformation.from(purchase))

}