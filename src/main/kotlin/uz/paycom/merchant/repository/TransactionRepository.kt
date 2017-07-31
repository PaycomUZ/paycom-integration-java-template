package uz.paycom.merchant.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import uz.paycom.merchant.entity.OrderTransaction
import uz.paycom.merchant.entity.TransactionState
import java.util.*

interface TransactionRepository: CrudRepository<OrderTransaction, Long> {
  fun findByPaycomId(id: String): OrderTransaction?

  @Query("select o from OrderTransaction o " +
    "where o.paycomTime between ?1 and ?2 and o.state = ?3 ORDER BY paycomTime ASC")
  fun findByPaycomTimeAndState(from: Date, to: Date, state: TransactionState): List<OrderTransaction>?
}