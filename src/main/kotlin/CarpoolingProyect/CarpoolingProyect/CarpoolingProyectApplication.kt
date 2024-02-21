package CarpoolingProyect.CarpoolingProyect

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class CarpoolingProyectApplication

fun main(args: Array<String>) {
	runApplication<CarpoolingProyectApplication>("-jvm-target")
}
