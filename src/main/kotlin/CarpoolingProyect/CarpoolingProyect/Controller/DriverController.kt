package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Model.Driver
import CarpoolingProyect.CarpoolingProyect.Service.DriverService
import CarpoolingProyect.CarpoolingProyect.Service.TokenService
import CarpoolingProyect.CarpoolingProyect.Dto.BasicErrorResponse
import CarpoolingProyect.CarpoolingProyect.Dto.DriverCreationRequest
import CarpoolingProyect.CarpoolingProyect.utils.getJwtCookie
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/driver")
class DriverController {
    @Autowired
    lateinit var driverService: DriverService

    @Autowired
    lateinit var tokenService: TokenService

    @GetMapping()
    fun getAll():ResponseEntity<*>{
        return ResponseEntity(driverService.listAllUsers(),HttpStatus.OK)
    }

    @PostMapping()
    fun createUser(@RequestBody user:Driver):ResponseEntity<Driver>{
        return ResponseEntity(driverService.saveUser(user),HttpStatus.OK)
    }


    @PostMapping("/createDriver")
    fun createDriver(requestServer: HttpServletRequest, @RequestBody driverCreationRequest: DriverCreationRequest):ResponseEntity<BasicErrorResponse>{
        val decodedId= tokenService.getJwtId(requestServer)
        return ResponseEntity(driverService.createDriver(decodedId,driverCreationRequest),HttpStatus.OK)
    }

    @GetMapping("/testDriver")
    fun testDriver():ResponseEntity<String>{
        driverService.testDriver()
        return ResponseEntity("Allo",HttpStatus.OK)
    }
    @PutMapping()
    fun updateUser(@RequestBody user:Driver):ResponseEntity<Driver>{
        return ResponseEntity(driverService.update(user),HttpStatus.OK)
    }

    @PatchMapping()
    fun updateName(@RequestBody user:Driver):ResponseEntity<Driver>{
        return ResponseEntity(driverService.updateLicence(user),HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id:Long?):ResponseEntity<Driver>{
        return ResponseEntity(driverService.listById(id),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteId(@PathVariable id:Long?):String{
        return driverService.deleteId(id)
    }
}