package com.ltusso.trader.transaction.sale.model

import com.ltusso.trader.coins.model.Coin
import com.ltusso.trader.customer.model.Customer
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "sale")
data class Sale(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne
        @JoinColumn(name = "customer_id")
        val customer: Customer,
        @ManyToOne
        @JoinColumn(name = "crypto_id")
        val coin: Coin,
        val amount: BigDecimal,
        val price: BigDecimal
)