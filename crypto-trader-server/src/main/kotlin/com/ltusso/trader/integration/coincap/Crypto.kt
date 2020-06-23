package com.ltusso.trader.integration.coincap

import java.math.BigDecimal

data class Crypto(val id: String,
                  val symbol: String,
                  val name: String,
                  val priceUsd: BigDecimal) {
}