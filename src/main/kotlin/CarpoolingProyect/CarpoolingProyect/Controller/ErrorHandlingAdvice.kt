package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Exceptions.BasisErrorException
import CarpoolingProyect.CarpoolingProyect.Exceptions.ErrorResponse
import CarpoolingProyect.CarpoolingProyect.Exceptions.NotFoundResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ErrorHandlingAdvice {
    @ExceptionHandler(NotFoundResponse::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFoundResponse(request: HttpServletRequest, ex: NotFoundResponse): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = "Failure",
            code = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.reasonPhrase,
            message = ex.message,
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
}