package com.ltusso.trader.service

import com.ltusso.trader.model.Crypto
import com.ltusso.trader.repository.CryptoRepository
import com.ltusso.trader.web.dto.CryptoDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CryptoService(
        @Autowired private val cryptoRepository: CryptoRepository
) {

    fun getCryptos(): List<Crypto> = cryptoRepository.findAll()

    fun getCryptoByCode(code: String): Crypto = cryptoRepository.findByCode(code).orElseThrow { throw RuntimeException("$code does not exist") }

    fun addCrypto(dto: CryptoDTO) {
        cryptoRepository.findByCode(dto.code).ifPresent { throw java.lang.RuntimeException("${dto.code} already exists") }
        cryptoRepository.save(Crypto(name = dto.name, description = dto.description, code = dto.code, price = dto.price))
    }

}