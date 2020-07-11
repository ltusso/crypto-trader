package com.ltusso.trader.transaction.purchase.web

import com.ltusso.trader.coins.web.dto.CryptoDTO
import com.ltusso.trader.transaction.purchase.model.PurchaseInformation
import com.ltusso.trader.transaction.purchase.service.PurchaseService
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
        return ResponseEntity.ok("Purchase done")
    }

    @RequestMapping("/{customerId}")
    fun getPurchases(@PathVariable customerId: Long): ResponseEntity<List<PurchaseDTO>> {
        val purchases = purchaseService.getPurchasesByCustomer(customerId)
        if (purchases.isNotEmpty()) {
            return ResponseEntity.ok(
                    purchases
                            .map { PurchaseDTO(CryptoDTO(it.coin.name, it.coin.code, it.coin.price), it.amount, customerId,it.price) }
            )
        }
        return ResponseEntity.ok(emptyList())
    }

    // Mappers
    fun PurchaseDTO.toPurchaseInformation(): PurchaseInformation {
        return PurchaseInformation(cryptoDTO.id, cryptoDTO.name, amount, customerId)
    }
}