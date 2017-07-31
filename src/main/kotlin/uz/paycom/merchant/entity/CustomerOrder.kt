package uz.paycom.merchant.entity

import javax.persistence.*

@Entity data class CustomerOrder(@Id val id: Long, val amount: Int, var delivered: Boolean)