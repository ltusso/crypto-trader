package com.ltusso.trader.web.dto

import java.math.BigDecimal
import javax.validation.constraints.Size

class PurchaseDTO(val cryptoDTO: CryptoDTO,
                  val amountToBuy: BigDecimal,
                  val priceUsd: BigDecimal,
                  val customerId: Long
)