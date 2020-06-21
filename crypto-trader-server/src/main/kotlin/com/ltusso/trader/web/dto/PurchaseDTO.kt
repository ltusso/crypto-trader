package com.ltusso.trader.web.dto

import java.math.BigDecimal

class PurchaseDTO(val cryptoDTO: CryptoDTO,
                  val amountToBuy: BigDecimal,
                  val priceUsd: BigDecimal,
                  val customerId: Long
)