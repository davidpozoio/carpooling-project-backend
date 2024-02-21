package CarpoolingProyect.CarpoolingProyect.Filters

import CarpoolingProyect.CarpoolingProyect.Global.Roles
import CarpoolingProyect.CarpoolingProyect.Service.TokenService
import CarpoolingProyect.CarpoolingProyect.Service.UserService
import CarpoolingProyect.CarpoolingProyect.utils.HttpExceptionUnauthorized
import CarpoolingProyect.CarpoolingProyect.utils.getJwtCookie
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpMethod
import org.springframework.web.filter.OncePerRequestFilter

class RoleValidatorFilter @Lazy @Autowired constructor(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val roles: List<String> = listOf(Roles.USER)
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtCookie = getJwtCookie(request)
        val decodedToken = tokenService.verify(jwtCookie.value)

        val userId = decodedToken.subject
        val user = userService.listById(userId.toLong())

        val hasUserAccess = roles.contains(user.role)

        if(!hasUserAccess){
            throw HttpExceptionUnauthorized("the user doesn't have access")
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        var allowedMethods = listOf(HttpMethod.POST.toString())
//        if(request.pathInfo == "/alerts"){
//            return allowedMethods.contains(request.method.toString())
//        }
//        return false
        return false
    }
}