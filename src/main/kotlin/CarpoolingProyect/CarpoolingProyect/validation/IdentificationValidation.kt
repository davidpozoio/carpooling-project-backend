package CarpoolingProyect.CarpoolingProyect.validation


import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class IdentificationValidation : ConstraintValidator<Identification, String> {

    override fun initialize(constraintAnnotation: Identification) {
        // Puedes realizar inicializaciones si es necesario
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return value != null && value.startsWith("A")
    }
}
