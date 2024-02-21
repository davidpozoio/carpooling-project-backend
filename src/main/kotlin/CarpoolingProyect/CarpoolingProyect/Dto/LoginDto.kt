package CarpoolingProyect.CarpoolingProyect.Dto

import jakarta.validation.constraints.NotBlank

class LoginDto {
    @field:NotBlank(message = "email is required validation")
    var email = ""
    @field:NotBlank(message = "password is required validation")
    var password = ""
}