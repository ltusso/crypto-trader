package com.ltusso.trader.web.dto

import com.ltusso.trader.model.Crypto

class CryptoDTO(val name: String, val id: String) {
    companion object {
        fun from(crypto: Crypto): CryptoDTO = CryptoDTO(name = crypto.name, id = crypto.code)
    }
}