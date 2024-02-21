package CarpoolingProyect.CarpoolingProyect.Dto

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.OK)
data class SuccessfulResponse (
    val code:String="200",
    val status:String="Success",
    val data:List<*>
)
@ResponseStatus(HttpStatus.CREATED)
data class SuccessfulCreation(
    val code:Int=HttpStatus.CREATED.value(),
    val status: String=HttpStatus.CREATED.toString(),
    val message: String
)