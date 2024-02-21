package CarpoolingProyect.CarpoolingProyect.Dto

import CarpoolingProyect.CarpoolingProyect.Model.Driver
import CarpoolingProyect.CarpoolingProyect.Model.Vehicle

data class DriverCreationRequest(
    val driver:Driver,
    val vehicle:Vehicle
)
