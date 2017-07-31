package uz.paycom.merchant

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.stereotype.Component

@Component
@EnableWebSecurity
class WebSecurityConfiguration: WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests().anyRequest()
        .authenticated().and().httpBasic()
        .and().csrf().disable()
  }
}