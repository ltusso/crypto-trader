package com.ltusso.trader.service

import com.ltusso.trader.model.Crypto
import com.ltusso.trader.repository.CryptoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CryptoService(
        @Autowired private val cryptoRepository: CryptoRepository
) {

    fun getCryptos(): List<Crypto> = cryptoRepository.findAll()

    fun getCryptoById(code: String): Crypto? {
        return cryptoRepository.findByCode(code)
    }

    fun addCrypto(code: String, price: BigDecimal, name: String): Crypto {
        if (getCryptoById(code) != null) {
            throw RuntimeException("Crypto $code already exists")
        }
        return cryptoRepository.save(Crypto(name = name, code = code, price = price))
    }

}