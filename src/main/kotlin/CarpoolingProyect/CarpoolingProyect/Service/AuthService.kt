package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.utils.HttpExceptionUnauthorized
import org.springframework.stereotype.Service

data class AuthUser(
                    val token: String,
                    val fullName: String,
                    val email: String, )

@Service
class AuthService (
    private val userService: UserService,
    private val tokenService: TokenService
){

    fun signUp(user: User): User{
        return userService.saveUser(user)
    }
    fun login(email: String, password: String): AuthUser{
        val user = userService.findByEmail(email)
        if(!user.comparePassword(password)){
            throw HttpExceptionUnauthorized("incorrect password")
        }

        return AuthUser(
            token = tokenService.create(user.id.toString()),
            fullName = user.firstName!!,
            email = user.email!!
        )
    }

    fun isAuth(token: String): User{
        val decodedToken = tokenService.verify(token)
        val userId = decodedToken.subject.toLong()
        return userService.listById(userId)
    }
}