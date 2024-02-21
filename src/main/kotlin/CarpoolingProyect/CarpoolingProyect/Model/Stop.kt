package CarpoolingProyect.CarpoolingProyect.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name = "stop")
class Stop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @NotBlank(message = "El nombre de la parada es obligatorio")
    var name: String? = null

    @NotBlank(message = "La descripcion es obligatoria")
    var description: String? = null

    @JsonIgnore
    @OneToMany(mappedBy = "stop",cascade = [CascadeType.ALL], orphanRemoval = true)
    var routeStop:MutableSet<RouteStop> = mutableSetOf()
}