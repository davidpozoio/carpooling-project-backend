package CarpoolingProyect.CarpoolingProyect.Service

import CarpoolingProyect.CarpoolingProyect.Model.Stop
import CarpoolingProyect.CarpoolingProyect.Repository.StopRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StopService {
    @Autowired
    lateinit var stopRepository: StopRepository
    fun listAll(searchTerm:String?): List<Stop> {
        if (!searchTerm.isNullOrEmpty()){
            return stopRepository.findStopsByName(searchTerm)
        }
        return stopRepository.findAll()
    }


    fun findById(id: Long) = stopRepository.findById(id)
    fun save(stop: Stop) = stopRepository.save(stop)
    fun delete(id: Long) = stopRepository.deleteById(id)

    fun update(stop: Stop): Stop{
        val stopToUpdate = stopRepository.findById(stop.id)?: throw Exception("stop not found")

        stop.name?: throw Exception("name required")
        stop.description?: throw  Exception("description required")

        stopToUpdate.apply {
            this.name = stop.name
            this.description = stop.description
        }
        return stopRepository.save(stopToUpdate)
    }

    fun patch(stop: Stop): Stop{
        val stopToUpdate = stopRepository.findById(stop.id)?: throw Exception("stop not found")

        stopToUpdate.apply {
            this.name = stop.name
            this.description = stop.description
        }
        return stopRepository.save(stopToUpdate)
    }
}