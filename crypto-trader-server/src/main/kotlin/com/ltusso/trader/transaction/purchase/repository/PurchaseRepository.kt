package com.ltusso.trader.transaction.purchase.repository

import com.ltusso.trader.transaction.purchase.model.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : JpaRepository<Purchase, Long> {
    fun findByCustomerId(customerId: Long): List<Purchase>


    @Query("SELECT C.code as coinCode,SUM(P.amount) as amount, SUM(P.price) as price from purchase P " +
            "inner join crypto C on c.id = P.crypto_id " +
            "where P.customer_id = :customerId " +
            "GROUP BY C.code",nativeQuery = true)
    fun findSumarizedByCustomerId(@Param("customerId") customerId: Long): List<SumarizedPurchase>
}