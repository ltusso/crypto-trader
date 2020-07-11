package com.ltusso.trader.transaction.sale.repository

import com.ltusso.trader.transaction.sale.model.Sale
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SaleRepository : JpaRepository<Sale, Long> {
    fun findByCustomerId(customerId: Long): List<Sale>
}