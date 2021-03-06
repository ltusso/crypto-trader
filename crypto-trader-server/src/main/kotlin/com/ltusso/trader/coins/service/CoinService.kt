package com.ltusso.trader.coins.service

import com.ltusso.trader.coins.model.Coin
import com.ltusso.trader.coins.repository.CoinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class CoinService(
        @Autowired private val coinRepository: CoinRepository
) {

    fun getCryptos(): List<Coin> = coinRepository.findAll()

    fun getCryptoById(code: String): Coin? {
        return coinRepository.findByCode(code)
    }

    fun addCrypto(code: String, price: BigDecimal, name: String): Coin {
        if (getCryptoById(code) != null) {
            throw RuntimeException("Crypto $code already exists")
        }
        return coinRepository.save(Coin(name = name, code = code, price = price))
    }

    fun updateCrypto(code: String, price: BigDecimal) {
        val crypto = coinRepository.findByCode(code)
        if (crypto != null) {
            crypto.variation = crypto.calculateVariationPercentage(price)
            crypto.price = price
            coinRepository.save(crypto)
        }
    }

    fun Coin.calculateVariationPercentage(newPrice: BigDecimal): BigDecimal {
        if (price.compareTo(BigDecimal.ZERO)!= 0) {
            return price.minus(newPrice)
                    .multiply(BigDecimal(100))
                    .divide(price, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal(-1))
        }
        return BigDecimal.ZERO
    }


}