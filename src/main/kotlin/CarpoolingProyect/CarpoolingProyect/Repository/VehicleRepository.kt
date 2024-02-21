package CarpoolingProyect.CarpoolingProyect.Repository

import CarpoolingProyect.CarpoolingProyect.Model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository : JpaRepository<Vehicle, Long?> {
    fun findById (id:Long?): Vehicle?
}
