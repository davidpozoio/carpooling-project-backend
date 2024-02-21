package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.Model.Vehicle
import CarpoolingProyect.CarpoolingProyect.Repository.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VehicleService {
    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    fun listAllVehicle(): List<Vehicle>{
        return vehicleRepository.findAll()
    }

    fun saveVehicle(vehicle: Vehicle): Vehicle{
        return vehicleRepository.save(vehicle)
    }

    fun update (vehicle: Vehicle): Vehicle{
        return vehicleRepository.save(vehicle)
    }

    fun updatePlate(vehicle: Vehicle): Vehicle{
        var response = vehicleRepository.findById(vehicle.id)?:throw Exception ("Id no existeSS")
        response.apply{
            plate=vehicle.plate
        }
        return vehicleRepository.save(response)
    }

    /*fun listById(id:Long?): Vehicle{
        val response = vehicleRepository.findById(id)?:throw Exception("Id no existe")
        return response
    }

    fun deleteId(id:Long?):String{
        try{
            vehicleRepository.findById(id)
                    ?: throw Exception("ID no existe")
            vehicleRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }*/
}