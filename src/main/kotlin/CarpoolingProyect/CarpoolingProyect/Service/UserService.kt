package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.Repository.UserRepository
import CarpoolingProyect.CarpoolingProyect.utils.HttpExceptionNotFound
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    //Basic Cruds
    fun listAllUsers():List<User>{
        return userRepository.findAll()
    }

    fun saveUser(user:User):User{
        return userRepository.save(user);
    }

    fun update(user:User): User {
        return userRepository.save(user)
    }

    fun updateCellNumber(user:User):User{
        val response = userRepository.findById(user.id)?:throw Exception("Id no existe")
        response.apply {
            cellNumber=user.cellNumber
        }
        return userRepository.save(response)

    }

    fun listById(id:Long?):User{
        val response = userRepository.findById(id)?:throw Exception("Id no existe")
        return response
    }

    fun findByEmail(email: String) = userRepository.findByEmail(email)?:
    throw HttpExceptionNotFound("user not found")

    fun deleteId(id:Long?):String{
        try{
            userRepository.findById(id)
                ?: throw Exception("ID no existe")
            userRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}