package com.ltusso.trader.web.dto

import java.math.BigDecimal
import javax.validation.constraints.Size

class PurchaseDTO(@Size(max = 3, min = 3) val cryptoCode: String,
                  val amountToBuy: BigDecimal,
                  val customerId: Long
)