package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Dto.LoginDto
import CarpoolingProyect.CarpoolingProyect.Dto.RegisterDto
import CarpoolingProyect.CarpoolingProyect.Dto.UserDto
import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.Service.AuthService
import CarpoolingProyect.CarpoolingProyect.utils.createJwtCookie
import CarpoolingProyect.CarpoolingProyect.utils.getJwtCookie
import CarpoolingProyect.CarpoolingProyect.utils.parseRegisterDtoToUser
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody user: RegisterDto): User{
        return authService.signUp(parseRegisterDtoToUser(user))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody user: LoginDto, bindingResult: BindingResult, response: HttpServletResponse): ResponseEntity<*> {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return a ResponseEntity with the error messages
            val errorMessages = bindingResult.allErrors.map { it.defaultMessage }.toList()
            return ResponseEntity.badRequest().body(mapOf("errors" to errorMessages))
        }

        val authUser = authService.login(user.email, user.password)
        val jwtCookie = createJwtCookie(authUser.token)
        response.addCookie(jwtCookie)
        return ResponseEntity.ok(UserDto().apply {
            fullName = authUser.fullName
            email = authUser.email
        })
    }

    @GetMapping("/me")
    fun isAuth(response: HttpServletRequest): ResponseEntity<UserDto> {
        val jwtCookie = getJwtCookie(response)
        val user = authService.isAuth(jwtCookie.value)



        return ResponseEntity.ok(UserDto().apply {
            fullName = user.firstName!!
            email = user.email!!
        })
    }

    @GetMapping("/logout")
    fun logout(response: HttpServletResponse){
        val deletedCookie = Cookie("jwt", null)

        // Set the maxAge to 0 to instruct the browser to remove the cookie
        deletedCookie.maxAge = 0

        // Set the path attribute to match the path of the original cookie
        deletedCookie.path = "/"

        // Add the new cookie to the response
        response.addCookie(deletedCookie)
    }
}