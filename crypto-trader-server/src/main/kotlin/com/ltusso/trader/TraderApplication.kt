package com.ltusso.trader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@ComponentScan("com.ltusso.trader")
@SpringBootApplication
class TraderApplication

fun main(args: Array<String>) {
	runApplication<TraderApplication>(*args)
}
