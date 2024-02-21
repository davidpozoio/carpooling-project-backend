package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Model.Vehicle
import CarpoolingProyect.CarpoolingProyect.Service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vehicle")
class VehicleController {
    @Autowired
    lateinit var vehicleService: VehicleService

    @GetMapping
    fun getAll(): ResponseEntity<*>{
        return ResponseEntity(vehicleService.listAllVehicle(), HttpStatus.OK)
    }

    @PostMapping()
    fun createVehicle(@RequestBody vehicle: Vehicle):ResponseEntity<Vehicle>{
        return ResponseEntity(vehicleService.saveVehicle(vehicle),HttpStatus.OK)
    }

    @PutMapping()
    fun updateVehicle(@RequestBody vehicle: Vehicle):ResponseEntity<Vehicle>{
        return ResponseEntity(vehicleService.update(vehicle),HttpStatus.OK)
    }

    @PatchMapping()
    fun updatePlate(@RequestBody vehicle: Vehicle):ResponseEntity<Vehicle>{
        return ResponseEntity(vehicleService.updatePlate(vehicle),HttpStatus.OK)
    }

    /*@GetMapping("/{id}")
    fun listById(@PathVariable id:Long?):ResponseEntity<Vehicle>{
        return ResponseEntity(vehicleService.listById(id),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteId(@PathVariable id:Long?):String{
        return vehicleService.deleteId(id)
    }*/
}