package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Dto.BasicErrorResponse
import CarpoolingProyect.CarpoolingProyect.Dto.ValidationErrorResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime


@ControllerAdvice
class ValidationExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<BasicErrorResponse> {
        val message = when {
            ex.message?.contains("violates unique constraint") == true -> "Valor duplicado. La clave única ya existe."
            else -> "Error de integridad de datos"
        }

        val response = BasicErrorResponse(
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.CONFLICT.value(),
            error = HttpStatus.CONFLICT.reasonPhrase,
            message = message
        )

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response)
    }
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: BindException): ResponseEntity<ValidationErrorResponse> {
        val errors = mutableMapOf<String, String>()

        val response = ValidationErrorResponse(
            timestamp = LocalDateTime.now().toString(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = "La solicitud contiene errores de validación",
            errors = ex.fieldErrors.map {
                ValidationErrorResponse.FieldError(
                    field = it.field,
                    message = it.defaultMessage ?: "Error de validación"
                )
            }
        );

        return ResponseEntity.badRequest().body(response)
    }
}
