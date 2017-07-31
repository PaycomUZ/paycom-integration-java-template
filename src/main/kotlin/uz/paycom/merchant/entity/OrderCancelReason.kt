package uz.paycom.merchant.entity

enum class OrderCancelReason(val code: Int) {
  RECEIVER_NOT_FOUND(1),
  DEBIT_OPERATION_ERROR(2),
  TRANSACTION_ERROR(3),
  TRANSACTION_TIMEOUT(4),
  MONEY_BACK(5),
  UNKNOWN_ERROR(10)
}