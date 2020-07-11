package com.ltusso.trader.coins.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "crypto")
data class Coin(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var name: String,
        @Column(unique = true)
        var code: String,
        @Column
        var description: String?=null,
        @Column
        var price: BigDecimal,
        @Column
        var variation: BigDecimal?=null
)