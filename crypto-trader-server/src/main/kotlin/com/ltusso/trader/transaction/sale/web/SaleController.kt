package com.ltusso.trader.transaction.sale.web

import com.ltusso.trader.coins.web.dto.CryptoDTO
import com.ltusso.trader.transaction.sale.model.SaleInformation
import com.ltusso.trader.transaction.sale.service.SaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class SaleController(@Autowired val saleService: SaleService) {

    @PostMapping
    fun SellCoin(@RequestBody saleDTO: SaleDTO): ResponseEntity<Any> {
        saleService.sell(saleDTO.toSaleInformation())
        return ResponseEntity.ok("Sold")
    }

    @RequestMapping("/{customerId}")
    fun getSales(@PathVariable customerId: Long): ResponseEntity<List<SaleDTO>> {
        val sales = saleService.getSalesByCustomer(customerId).orEmpty()
        if (sales.isNotEmpty()) {
            return ResponseEntity.ok(
                    sales
                            .map { SaleDTO(CryptoDTO(it.coin.name, it.coin.code, it.coin.price), it.amount, customerId) }
            )
        }
        return ResponseEntity.ok(emptyList())
    }

    // Mappers
    fun SaleDTO.toSaleInformation(): SaleInformation {
        return SaleInformation(cryptoDTO.id, amount, customerId)
    }
}