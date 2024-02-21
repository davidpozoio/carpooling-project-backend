package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Service.RouteStopService
import CarpoolingProyect.CarpoolingProyect.Service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/routes-stops")
class RouteStopController {
    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    lateinit var routeStopService: RouteStopService

    @GetMapping
    fun findAll() = routeStopService.findAll()
/*
    @PostMapping
    fun addRoutes(@Valid @RequestBody routeStop: RouteStopRequest,requestServer: HttpServletRequest):ResponseEntity<*>{
        val jwt: Cookie = getJwtCookie(requestServer);
        var decodedId=tokenService.verify(jwt.value).subject.toLong()

    }
    */

}