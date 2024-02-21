package CarpoolingProyect.CarpoolingProyect.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.Serializable
import java.lang.RuntimeException
open class BasisErrorException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.NOT_FOUND)
data class NotFoundResponse(
    val code:String="404",
    val status:String="Not Found",
    override val message:String="Recurso no Encontrado"
):RuntimeException(message),Serializable

data class ErrorResponse(
    val status: String="",
    val code: Int=0,
    val error: String="",
    val message: String=""
)

