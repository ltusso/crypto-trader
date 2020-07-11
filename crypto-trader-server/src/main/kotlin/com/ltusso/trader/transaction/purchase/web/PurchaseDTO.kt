package com.ltusso.trader.transaction.purchase.web

import com.ltusso.trader.coins.web.dto.CryptoDTO
import java.math.BigDecimal

class PurchaseDTO(val cryptoDTO: CryptoDTO,
                  val amount: BigDecimal,
                  val customerId: Long,
                  val price: BigDecimal? = null
)