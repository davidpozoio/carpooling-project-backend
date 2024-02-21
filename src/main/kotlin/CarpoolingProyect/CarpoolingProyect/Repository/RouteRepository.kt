package CarpoolingProyect.CarpoolingProyect.Repository

import CarpoolingProyect.CarpoolingProyect.Model.Route
import org.springframework.data.jpa.repository.JpaRepository

interface RouteRepository : JpaRepository<Route,Long?>{
    fun findById (id:Long?): Route?

    fun findByDriverId(driverId:Long?): List<Route>
}