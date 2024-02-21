package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Repository.RouteStopRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RouteStopService {
    @Autowired
    lateinit var routeStopRepository: RouteStopRepository

    fun findAll() = routeStopRepository.findAll()

    //fun addStops(routeStop)
}