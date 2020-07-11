package com.ltusso.trader.service

import com.ltusso.trader.model.Crypto
import com.ltusso.trader.repository.CryptoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

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

    fun updateCrypto(code: String, price: BigDecimal) {
        var crypto = cryptoRepository.findByCode(code)
        if (crypto != null) {
            crypto.variation = crypto.calculateVariationPercentage(price)
            crypto.price = price
            cryptoRepository.save(crypto)
        }
    }

    fun Crypto.calculateVariationPercentage(newPrice: BigDecimal): BigDecimal {
        if (price.compareTo(BigDecimal.ZERO)!= 0) {
            return price.minus(newPrice)
                    .multiply(BigDecimal(100))
                    .divide(price, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal(-1))
        }
        return BigDecimal.ZERO
    }


}