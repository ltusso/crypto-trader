package com.ltusso.trader.coins.repository

import com.ltusso.trader.coins.model.Coin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoinRepository : JpaRepository<Coin, Long>{
    fun findByCode(code: String): Coin?
}