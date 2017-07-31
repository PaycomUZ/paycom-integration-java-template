package uz.paycom.merchant.service

import com.googlecode.jsonrpc4j.JsonRpcError
import com.googlecode.jsonrpc4j.JsonRpcErrors
import com.googlecode.jsonrpc4j.JsonRpcParam
import com.googlecode.jsonrpc4j.JsonRpcService
import uz.paycom.merchant.common.exception.*
import uz.paycom.merchant.entity.OrderCancelReason
import uz.paycom.merchant.entity.json.Account
import uz.paycom.merchant.entity.json.Transactions
import uz.paycom.merchant.entity.json.result.*
import java.util.*

@JsonRpcService("/api") interface IMerchantService {
  @JsonRpcErrors(JsonRpcError(exception = WrongAmountException::class, code = -31001, message = "Wrong amount", data = "amount"),
    JsonRpcError(exception = OrderNotExistsException::class, code = -31050, message = "Order not found", data = "order"))
  fun CheckPerformTransaction(@JsonRpcParam(value = "amount") amount: Int, @JsonRpcParam(value = "account") account: Account): CheckPerformTransactionResult

  @JsonRpcErrors(JsonRpcError(exception = UnableCompleteException::class, code = -31008, message = "Unable to complete operation", data = "transaction"))
  fun CreateTransaction(@JsonRpcParam(value = "id") id: String, @JsonRpcParam(value = "time") time: Date,
                        @JsonRpcParam(value = "amount") amount: Int, @JsonRpcParam(value = "account") account: Account): CreateTransactionResult

  @JsonRpcErrors(JsonRpcError(exception = UnableCompleteException::class, code = -31008, message = "Unable to complete operation", data = "transaction"),
    JsonRpcError(exception = TransactionNotFoundException::class, code = -31003, message = "Order transaction not found", data = "transaction"))
  fun PerformTransaction(@JsonRpcParam(value = "id") id: String): PerformTransactionResult

  @JsonRpcErrors(JsonRpcError(exception = UnableCancelTransactionException::class, code = -31007, message = "Unable to cancel transaction", data = "transaction"),
    JsonRpcError(exception = TransactionNotFoundException::class, code = -31003, message = "Order transaction not found", data = "transaction"))
  fun CancelTransaction(@JsonRpcParam(value = "id") id: String, @JsonRpcParam(value = "reason") reason: OrderCancelReason): CancelTransactionResult

  @JsonRpcErrors(JsonRpcError(exception = TransactionNotFoundException::class, code = -31003, message = "Order transaction not found", data = "transaction"))
  fun CheckTransaction(@JsonRpcParam(value = "id") id: String): CheckTransactionResult

  fun GetStatement(@JsonRpcParam(value = "from") from: Date, @JsonRpcParam(value = "to") to: Date): Transactions

  fun ChangePassword(@JsonRpcParam(value = "password") password: String): ChangePasswordResult
}