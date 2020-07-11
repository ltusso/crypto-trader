package com.ltusso.trader.transaction.sale.web

import com.ltusso.trader.coins.web.dto.CryptoDTO
import java.math.BigDecimal

class SaleDTO(val cryptoDTO: CryptoDTO,
              val amount: BigDecimal,
              val customerId: Long
)