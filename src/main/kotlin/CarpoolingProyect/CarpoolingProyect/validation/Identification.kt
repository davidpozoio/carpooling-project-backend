package CarpoolingProyect.CarpoolingProyect.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [IdentificationValidation::class])
annotation class Identification(
    val message: String = "La cedula es Invalida",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
