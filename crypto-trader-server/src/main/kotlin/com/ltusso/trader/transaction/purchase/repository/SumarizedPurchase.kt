package com.ltusso.trader.transaction.purchase.repository

import java.math.BigDecimal

interface SumarizedPurchase {
    fun getCoincode(): String
    fun getAmount(): BigDecimal
    fun getPrice(): BigDecimal
}


