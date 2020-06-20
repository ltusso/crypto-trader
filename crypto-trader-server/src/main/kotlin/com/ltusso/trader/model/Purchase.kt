package com.ltusso.trader.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "purchase")
data class Purchase(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne
        @JoinColumn(name = "customer_id")
        val customer: Customer,
        @ManyToOne
        @JoinColumn(name = "crypto_id")
        val crypto: Crypto,
        val purchasedAmount: BigDecimal,
        val price: BigDecimal
)