package com.ltusso.trader.repository

import com.ltusso.trader.model.Crypto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CryptoRepository : JpaRepository<Crypto, Long>{
    fun findByCode(code:String):Optional<Crypto>
}