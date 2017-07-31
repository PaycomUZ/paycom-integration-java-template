package uz.paycom.merchant

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import uz.paycom.merchant.common.exception.OrderNotExistsException
import uz.paycom.merchant.common.exception.TransactionNotFoundException
import uz.paycom.merchant.common.exception.UnableCompleteException
import uz.paycom.merchant.entity.CustomerOrder
import uz.paycom.merchant.entity.json.Account
import uz.paycom.merchant.repository.OrderRepository
import uz.paycom.merchant.service.impl.MerchantService
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class MerchantApplicationTests {

	@Autowired lateinit var service: MerchantService
	@Autowired lateinit var repository: OrderRepository

	var dataLoaded = false

	@Before fun prepare() {
		if (!dataLoaded) {
			repository.save(CustomerOrder(100, 50000, true))
			repository.save(CustomerOrder(101, 55000, false))
			repository.save(CustomerOrder(102, 60000, false))
			dataLoaded = true
		}
	}

	@Test fun testCheckPerformTransactionSuccess() {
		val positiveResult = service.CheckPerformTransaction(50000, Account(100)).allow
		assert(positiveResult)
	}

	@Test(expected = OrderNotExistsException::class) fun testPerformTransactionNegative() {
		service.CheckPerformTransaction(40000, Account(200))
	}

	@Test fun checkCreateTransactionSuccess() {
		val result = service.CreateTransaction("5305e3bab097f420a62ced0b", Date(), 50000, Account(100))
		Assert.assertEquals(result.state, 1)
	}

	@Test(expected = UnableCompleteException::class) fun checkCreateTransactionNegative() {
		service.CreateTransaction("4305e3bab097f420a62ced0b", Date(1399114284039), 60000, Account(102))
		service.CreateTransaction("4305e3bab097f420a62ced0b", Date(1399114284039), 60000, Account(102))
	}

	@Test fun checkPerformTransactionSuccess() {
		val result = service.PerformTransaction("5305e3bab097f420a62ced0b")
		Assert.assertEquals(result.state, 2)
	}

	@Test(expected = TransactionNotFoundException::class) fun checkPerformTransactionNegative() {
		service.PerformTransaction("4305e3bab097f420a62ced0b")
	}
}