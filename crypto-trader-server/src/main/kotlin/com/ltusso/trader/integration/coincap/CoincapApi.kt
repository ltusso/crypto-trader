package com.ltusso.trader.integration.coincap

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate

@Component
class CoincapApi {

    @Value("\${coincapUrl.assets:https://api.coincap.io/v2/assets}")
    lateinit var assetsUrl: String

    fun getCryptoList(): List<Crypto> {
        var list: List<Crypto> = arrayListOf()
        try {
            list = RestTemplate().exchange(assetsUrl, HttpMethod.GET, buildHeader(), Array<Crypto>::class.java).body as List<Crypto>
        } catch (e: RestClientResponseException) {
            println("Can't syncrhonize. There has been an error while trying to fetch assets from Coincap.")
        }
        return list
    }

    private fun buildHeader(): HttpEntity<Any> {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.add("user-agent", "\"user-agent\", \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36\")")
        return HttpEntity("parameters", headers)
    }
}

