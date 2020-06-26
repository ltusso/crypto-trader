package com.ltusso.trader.web.dto

import java.math.BigDecimal

class PurchaseDTO(val cryptoDTO: CryptoDTO,
                  val amount: BigDecimal,
                  val price: BigDecimal,
                  val customerId: Long
)