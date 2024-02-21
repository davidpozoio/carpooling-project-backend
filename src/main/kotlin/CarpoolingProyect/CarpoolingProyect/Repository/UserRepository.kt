package CarpoolingProyect.CarpoolingProyect.Repository

import CarpoolingProyect.CarpoolingProyect.Model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User,Long?>{
    fun findById (id:Long?): User?
    @Query("SELECT * FROM users WHERE email = :email", nativeQuery = true)
    fun findByEmail(@Param("email") email: String): User?
}