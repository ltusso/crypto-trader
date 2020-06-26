package com.ltusso.trader.repository

import com.ltusso.trader.model.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : JpaRepository<Purchase, Long> {
    fun findByCustomerId(customerId: Long): List<Purchase>
}