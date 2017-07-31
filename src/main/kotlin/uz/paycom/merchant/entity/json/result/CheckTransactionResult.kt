package uz.paycom.merchant.entity.json.result

import uz.paycom.merchant.entity.OrderCancelReason
import java.util.*

class CheckTransactionResult(val create_time: Date? = null, val perform_time: Date? = null, val cancel_time: Date? = null,
                             val transaction: Long? = null, val state: Int? = null, val reason: Int? = null)