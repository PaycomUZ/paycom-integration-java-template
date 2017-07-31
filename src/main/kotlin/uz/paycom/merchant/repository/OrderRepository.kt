package uz.paycom.merchant.repository

import org.springframework.data.repository.CrudRepository
import uz.paycom.merchant.entity.CustomerOrder

interface OrderRepository: CrudRepository<CustomerOrder, Long>