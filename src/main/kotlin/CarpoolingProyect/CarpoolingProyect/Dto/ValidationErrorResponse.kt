package CarpoolingProyect.CarpoolingProyect.Dto
data class ValidationErrorResponse(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
    val errors: List<FieldError>
) {
    data class FieldError(
        val field: String,
        val message: String
    )
}

data class BasicErrorResponse(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String
)