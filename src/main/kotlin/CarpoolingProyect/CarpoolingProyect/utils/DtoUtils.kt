package CarpoolingProyect.CarpoolingProyect.utils

import CarpoolingProyect.CarpoolingProyect.Dto.RegisterDto
import CarpoolingProyect.CarpoolingProyect.Model.User

fun parseRegisterDtoToUser(registerDto: RegisterDto): User{
    return User().apply {
        firstName=registerDto.fullName
        lastName = "asd"
        identification=registerDto.identification
        email=registerDto.email
        password = registerDto.password
        cellNumber = "1234"
    }
}