package CarpoolingProyect.CarpoolingProyect.Repository

import CarpoolingProyect.CarpoolingProyect.Model.Stop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StopRepository: JpaRepository<Stop, Long> {

    fun findById(id: Long?): Stop?
    @Query("SELECT s FROM Stop s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    fun findStopsByName(searchTerm: String): List<Stop>
}