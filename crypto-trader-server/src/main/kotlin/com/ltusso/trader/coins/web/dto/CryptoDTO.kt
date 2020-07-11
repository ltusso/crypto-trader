package com.ltusso.trader.coins.web.dto

import java.math.BigDecimal

class CryptoDTO(val name: String,
                val id: String,
                val price: BigDecimal?=null,
                val variation: BigDecimal?=null)