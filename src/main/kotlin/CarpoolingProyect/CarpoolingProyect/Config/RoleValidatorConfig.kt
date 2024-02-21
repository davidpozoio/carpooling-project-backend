package CarpoolingProyect.CarpoolingProyect.Config

import CarpoolingProyect.CarpoolingProyect.Filters.RoleValidatorFilter
import CarpoolingProyect.CarpoolingProyect.Global.Roles
import CarpoolingProyect.CarpoolingProyect.Service.TokenService
import CarpoolingProyect.CarpoolingProyect.Service.UserService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod

@Configuration
class RoleValidatorConfig {
    @Bean
    fun adminRoleValidatorFilter(tokenService: TokenService, userService: UserService): FilterRegistrationBean<RoleValidatorFilter> {
        val registrationBean = FilterRegistrationBean<RoleValidatorFilter>()
        registrationBean.filter = RoleValidatorFilter(tokenService,
            userService,
            listOf(Roles.USER))

        val allowedMethods = listOf(HttpMethod.POST.toString())
        registrationBean.addUrlPatterns("/stopsa/*")

        return  registrationBean
    }

}