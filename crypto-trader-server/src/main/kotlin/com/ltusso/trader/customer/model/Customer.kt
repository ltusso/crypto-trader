package com.ltusso.trader.customer.model

import java.math.BigDecimal
import javax.persistence.*


@Entity
@Table(name = "customer")
data class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        @Column
        var name: String,
        @Column
        var lastName: String,
        @Column
        var budget: BigDecimal,
        @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        var assets: List<CustomerAsset>
)