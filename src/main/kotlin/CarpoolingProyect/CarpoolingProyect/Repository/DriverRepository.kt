package CarpoolingProyect.CarpoolingProyect.Repository

import CarpoolingProyect.CarpoolingProyect.Model.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver,Long?>{
    fun findById (id:Long?): Driver?
}