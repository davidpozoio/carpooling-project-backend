package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Dto.SuccessfulCreation
import CarpoolingProyect.CarpoolingProyect.Dto.SuccessfulResponse
import CarpoolingProyect.CarpoolingProyect.Exceptions.NotFoundResponse
import CarpoolingProyect.CarpoolingProyect.Model.Route
import CarpoolingProyect.CarpoolingProyect.Dto.RouteStopRequest
import CarpoolingProyect.CarpoolingProyect.Model.Stop
import CarpoolingProyect.CarpoolingProyect.Repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class RouteService {
    @Autowired
    lateinit var stopRepository: StopRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    lateinit var routeRepository: RouteRepository

    @Autowired
    lateinit var driverRepository: DriverRepository

    @Autowired
    lateinit var routeStopRepository: RouteStopRepository
    //Basic Cruds
    fun listAllUsers():List<Route>{
        return routeRepository.findAll()
    }

    fun listUserRoutes(id:Long): ArrayList<*>{
        val userRoutes=userRepository.findById(id).get().driver!!.route

        return ArrayList(userRoutes)

    }
    @Transactional
    fun saveRoute(route: Route,id: Long): Route {
        val user=userRepository.findById(id).get()
        val driver=driverRepository.findById(user.driver!!.id)
        driver!!.route.add(route)
        route.driver=driver
        driverRepository.save(driver)

        return routeRepository.save(route)

    }

    @Transactional
    fun addRouteStops(id:Long,routeStopRequest: RouteStopRequest):SuccessfulCreation{
        val user=userRepository.findById(id).get()
        val userRoutes= user.driver!!.route
        val requestedStops=routeStopRequest.routeStops
        if(userRoutes.isNotEmpty()){
            if (userRoutes.any{it.id==routeStopRequest.routeId}){
                val userRoute=routeRepository.findById(routeStopRequest.routeId).get()
                var stop: Stop?;
                requestedStops.forEach {
                    it.route=userRoute
                    stop=stopRepository.findById(it.stopId)
                    it.stop=stop
                }
                routeStopRepository.saveAll(requestedStops)
                userRoute.routeStop.addAll(requestedStops)
                routeRepository.save(userRoute)
                return SuccessfulCreation(message = "User Route Creada Correctamente")
            }
            throw NotFoundResponse(message = "El id de Ruta no Pertenece al Usuario o no Existe")
        }
        throw NotFoundResponse(message = "El usuario no tiene rutas creadas")
    }

    fun update(user: Route): Route {
        return routeRepository.save(user)
    }

    fun updateDescription(user: Route): Route {
        val response = routeRepository.findById(user.id)?:throw Exception("Id no existe")
        response.apply {
            description=user.description
        }
        return routeRepository.save(response)

    }

    fun listById(id: Long?): Route {
        return routeRepository.findById(id) ?: throw Exception("Id no existe")
    }

    fun deleteId(id:Long?):String{
        try{
            routeRepository.findById(id)
                ?: throw Exception("ID no existe")
            routeRepository.deleteById(id!!)
            return "ID eliminado Correctamente!!!"
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}