package CarpoolingProyect.CarpoolingProyect.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date
import kotlin.jvm.Transient

@Entity
@Table(name = "route_stop")
class RouteStop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null

    var position: Long? = null

    @NotNull(message = "La hora de llegada es obligatoria")
    @Column(name="arrive_hour")
    var arriveHour: Date? = null

    @ManyToOne
    @JoinColumn(name = "stop_id")
    var stop: Stop? = null

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "route_id")
    var route: Route? = null

    @Transient
    var stopId:Long?=null
}