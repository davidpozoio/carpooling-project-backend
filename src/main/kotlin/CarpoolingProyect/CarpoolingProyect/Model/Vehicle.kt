package CarpoolingProyect.CarpoolingProyect.Model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name="vehicle")
class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @NotBlank(message="La placa es Obligatorio") //validate
    @Column(name="plate")
    var plate: String? = null

    @NotBlank(message="El color es Obligatorio") //validate
    @Column(name="color")
    var color: String? = null

    @NotBlank(message="El modelo de carro Obligatorio") //validate
    @Column(name="model")
    var model: String? = null

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "driver_id",referencedColumnName = "id")
    var driver: Driver? = null

}
