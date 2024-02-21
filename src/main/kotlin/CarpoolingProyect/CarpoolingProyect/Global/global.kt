package CarpoolingProyect.CarpoolingProyect.Global

import io.jsonwebtoken.Jwts

object SecretModule{
    val SECRET = Jwts.SIG.HS512.key().build()
}

object Roles{
    val ADMIN = "ADMIN"
    val USER = "USER"
    val DRIVER = "DRIVER"
}