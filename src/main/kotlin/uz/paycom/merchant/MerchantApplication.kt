package uz.paycom.merchant

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import uz.paycom.merchant.entity.CustomerOrder
import uz.paycom.merchant.repository.OrderRepository

@SpringBootApplication open class MerchantApplication {

  companion object {
    @JvmStatic fun main(args: Array<String>) {
      SpringApplication.run(MerchantApplication::class.java, *args)
    }

    @Bean fun autoJsonExporter() = AutoJsonRpcServiceImplExporter()
  }

  @Bean fun prepare(repository: OrderRepository): CommandLineRunner {
    //Add sample orders
    return CommandLineRunner {
      repository.save(CustomerOrder(100, 50000, true))
      repository.save(CustomerOrder(101, 55000, false))
      repository.save(CustomerOrder(102, 60000, false))
    }
  }
}