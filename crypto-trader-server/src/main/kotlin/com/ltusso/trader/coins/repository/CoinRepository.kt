package com.ltusso.trader.repository

import com.ltusso.trader.model.Crypto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CryptoRepository : JpaRepository<Crypto, Long>{
    fun findByCode(code: String): Crypto?
}