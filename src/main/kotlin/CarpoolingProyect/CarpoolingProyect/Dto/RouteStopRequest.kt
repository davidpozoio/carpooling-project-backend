package CarpoolingProyect.CarpoolingProyect.Dto

import CarpoolingProyect.CarpoolingProyect.Model.RouteStop

data class RouteStopRequest (
    var routeId:Long=0,
    var routeStops:List<RouteStop>
)