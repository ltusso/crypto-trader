package com.ltusso.trader.web.dto

import com.ltusso.trader.model.Crypto
import java.math.BigDecimal
import javax.validation.constraints.Size

class CryptoDTO(val name: String, val description: String, @Size(max = 3, min = 3) val code: String, val price: BigDecimal) {
    companion object {
        fun from(crypto: Crypto): CryptoDTO = CryptoDTO(crypto.name, crypto.description, crypto.code, crypto.price)
    }
}