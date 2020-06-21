package com.ltusso.trader.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "crypto")
data class Crypto(
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
        var price: BigDecimal?=null
)