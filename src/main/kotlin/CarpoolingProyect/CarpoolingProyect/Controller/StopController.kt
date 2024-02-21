package CarpoolingProyect.CarpoolingProyect.Controller

import CarpoolingProyect.CarpoolingProyect.Model.Stop
import CarpoolingProyect.CarpoolingProyect.Model.User
import CarpoolingProyect.CarpoolingProyect.Service.StopService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stops")
class StopController {
    @Autowired
    lateinit var stopService: StopService

    @GetMapping
    fun listAll(@RequestParam searchTerm: String?) = stopService.listAll(searchTerm)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = stopService.findById(id)

    @PostMapping()
    fun save(@Valid @RequestBody stop: Stop) = stopService.save(stop)

    @DeleteMapping
    fun delete(id: Long) = stopService.delete(id)

    @PatchMapping
    fun patch(stop: Stop) = stopService.patch(stop)

    @PutMapping
    fun put(stop: Stop) = stopService.update(stop)
}