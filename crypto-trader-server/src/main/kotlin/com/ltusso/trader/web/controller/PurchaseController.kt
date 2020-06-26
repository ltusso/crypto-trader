package com.ltusso.trader.web.controller

import com.ltusso.trader.service.PurchaseInformation
import com.ltusso.trader.service.PurchaseService
import com.ltusso.trader.web.dto.CryptoDTO
import com.ltusso.trader.web.dto.PurchaseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class PurchaseController(@Autowired val purchaseService: PurchaseService) {

    @PostMapping
    fun purchaseCrypto(@RequestBody purchaseDto: PurchaseDTO): ResponseEntity<Any> {
        purchaseService.purchase(purchaseDto.toPurchaseInformation())
        return ResponseEntity.ok(ResponseEntity.ok());
    }

    @RequestMapping("/{customerId}")
    fun getPurchases(@PathVariable customerId: Long): ResponseEntity<List<PurchaseDTO>> {
        val purchases = purchaseService.getPurchasesByCustomer(customerId).orEmpty()
        if (purchases.isNotEmpty()) {
            return ResponseEntity.ok(
                    purchaseService.getPurchasesByCustomer(customerId)
                            .map { PurchaseDTO(CryptoDTO(it.crypto.name, it.crypto.code, it.crypto.price), it.purchasedAmount, it.price, customerId) }
            )
        }
        return ResponseEntity.ok(emptyList())
    }

    // Mappers
    fun PurchaseDTO.toPurchaseInformation(): PurchaseInformation {
        return PurchaseInformation(cryptoDTO.id, cryptoDTO.name, amount, price, customerId)
    }
}