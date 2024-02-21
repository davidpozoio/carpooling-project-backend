package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Model.Driver
import CarpoolingProyect.CarpoolingProyect.Repository.DriverRepository
import CarpoolingProyect.CarpoolingProyect.Repository.UserRepository
import CarpoolingProyect.CarpoolingProyect.Dto.BasicErrorResponse
import CarpoolingProyect.CarpoolingProyect.Dto.DriverCreationRequest
import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.Repository.VehicleRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class DriverService {
    @Autowired
    lateinit var driverRepository: DriverRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var vehicleRepository: VehicleRepository
    //Basic Cruds
    fun listAllUsers():List<Driver>{
        return driverRepository.findAll()
    }

    fun saveUser(user: Driver): Driver {
        return driverRepository.save(user);
    }

    fun testDriver(){
        val user=userRepository.findById(1).get()
        user.driver?.driverLicence ="mamamama"
        userRepository.save(user)
    }
    @Transactional
    fun createDriver(userId:Long,driverCreationRequest: DriverCreationRequest): BasicErrorResponse {
        val user=userRepository.findById(userId).get()
        val vehicle=driverCreationRequest.vehicle
        val driver=driverCreationRequest.driver
            user.driver=driver
            driver.user=user
            vehicle.driver=driver
            driverRepository.save(driver)
            userRepository.save(user)
            vehicleRepository.save(vehicle)
            return BasicErrorResponse(
                timestamp = LocalDateTime.now().toString(),
                status = HttpStatus.OK.value(),
                error = "None",
                message = "Success"
            )
    }

    fun update(user: Driver): Driver {
        return driverRepository.save(user)
    }

    fun updateLicence(user: Driver): Driver {
        val response = driverRepository.findById(user.id)?:throw Exception("Id no existe")
        response.apply {
            driverLicence=user.driverLicence
        }
        return driverRepository.save(response)

    }

    fun listById(id: Long?): Driver {
        return driverRepository.findById(id) ?: throw Exception("Id no existe")
    }

    fun deleteId(id:Long?):String{
        try{
            driverRepository.findById(id)
                ?: throw Exception("ID no existe")
            driverRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}