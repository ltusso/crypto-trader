package com.ltusso.trader.customer.model

import com.ltusso.trader.coins.model.Coin
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "CustomerAsset")
data class CustomerAsset(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @OneToOne
        val coin: Coin,
        @ManyToOne
        @JoinColumn(name = "customer_id")
        val customer: Customer,
        var amount: BigDecimal

)