package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.Service.AuthService
import CarpoolingProyect.CarpoolingProyect.Service.TokenService
import CarpoolingProyect.CarpoolingProyect.Service.UserService
import CarpoolingProyect.CarpoolingProyect.utils.getJwtCookie
import CarpoolingProyect.CarpoolingProyect.utils.parseRegisterDtoToUser
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Validated
class UserController {
    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var tokenService: TokenService
    @GetMapping()
    fun getAll(requestServer:HttpServletRequest):ResponseEntity<*>{
        var setso:Cookie=getJwtCookie(requestServer);
        var decoded=tokenService.verify(setso.value)
        return ResponseEntity(userService.listAllUsers(),HttpStatus.OK)
    }

    @PostMapping("/create-user")
    fun createUser2(@Valid @RequestBody user: User):ResponseEntity<*>{
        return ResponseEntity(authService.signUp(user),HttpStatus.OK)
    }
    @PostMapping()
    fun createUser(@Valid @RequestBody user:User):ResponseEntity<User>{
        return ResponseEntity(userService.saveUser(user),HttpStatus.OK)
    }

    @PutMapping()
    fun updateUser(@RequestBody user:User):ResponseEntity<User>{
        return ResponseEntity(userService.update(user),HttpStatus.OK)
    }

    @PatchMapping()
    fun updateName(@RequestBody user:User):ResponseEntity<User>{
        return ResponseEntity(userService.updateCellNumber(user),HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id:Long?):ResponseEntity<User>{
        return ResponseEntity(userService.listById(id),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteId(@PathVariable id:Long?):String{
        return userService.deleteId(id)
    }
}