package uz.paycom.merchant.entity

import java.util.*
import javax.persistence.*

@Entity data class OrderTransaction(@Id @GeneratedValue val id: Long = 0L, val paycomId: String,
                                    @Temporal(TemporalType.TIMESTAMP) val paycomTime: Date,
                                    @Temporal(TemporalType.TIMESTAMP) val createTime: Date,
                                    @Temporal(TemporalType.TIMESTAMP) var performTime: Date? = null,
                                    @Temporal(TemporalType.TIMESTAMP) var cancelTime: Date? = null,
                                    var reason: OrderCancelReason? = null, var state: TransactionState,
                                    @OneToOne val order: CustomerOrder?)