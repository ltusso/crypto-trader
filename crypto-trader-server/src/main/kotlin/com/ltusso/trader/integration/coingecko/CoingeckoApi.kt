package com.ltusso.trader.integration.coingecko

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate


@Component
class CoingeckoApi {

    @Value("\${coingeckoUrl.coins.market:https://api.coingecko.com/api/v3/coins/markets}")
    lateinit var coinsMarketUrl: String

    fun getCryptoList(): List<CoingeckoCrypto> {
        var list: List<CoingeckoCrypto> = arrayListOf()
        try {
            val responseType: ParameterizedTypeReference<List<CoingeckoCrypto?>?> = object : ParameterizedTypeReference<List<CoingeckoCrypto?>?>() {}
            val resp: ResponseEntity<List<CoingeckoCrypto?>?> = RestTemplate().exchange(coinsMarketUrl, HttpMethod.GET, buildHeader(), responseType)
             list = resp.getBody() as List<CoingeckoCrypto>
        } catch (e: RestClientResponseException) {
            println("Can't syncrhonize. There has been an error while trying to fetch assets from Coingecko.")
        }
        return list
    }

    private fun buildHeader(): HttpEntity<Any> {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        return HttpEntity("parameters", headers)
    }
}