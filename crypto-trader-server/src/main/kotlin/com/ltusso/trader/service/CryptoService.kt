package com.ltusso.trader.service

import com.ltusso.trader.model.Crypto
import com.ltusso.trader.repository.CryptoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CryptoService(
        @Autowired private val cryptoRepository: CryptoRepository
) {

    fun getCryptos(): List<Crypto> = cryptoRepository.findAll()

    fun getCryptoById(code: String): Optional<Crypto> = cryptoRepository.findByCode(code)

    fun addCrypto(crypto: Crypto): Crypto {
        val existingCrypto = cryptoRepository.findByCode(crypto.code)
        return if (existingCrypto.isPresent) {
            existingCrypto.get()
        } else {
            cryptoRepository.save(Crypto(name = crypto.name, description = crypto.description, code = crypto.code, price = crypto.price))
        }
    }

}