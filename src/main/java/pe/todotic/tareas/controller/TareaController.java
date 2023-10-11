package pe.todotic.tareas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.todotic.tareas.model.Tarea;
import pe.todotic.tareas.repo.TareaRepository;

import java.util.List;

@CrossOrigin
@RestController
@Controller
@RequestMapping("/tareas")
public class TareaController {
    @Autowired
    private TareaRepository tareaRepositary;

    @GetMapping("")
    List<Tarea> index() {
        return tareaRepositary.findAll();
    }

    /*/
    @GetMapping("")
    List<Tarea> index() {
        return tareaRepositary.findAll();
    }

*/


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Tarea create(@RequestBody Tarea tarea){
        return tareaRepositary.save(tarea);
    }

    @PutMapping("{id}")
    Tarea update (@PathVariable String id, @RequestBody Tarea tarea){
        Tarea tareaFromDB = tareaRepositary
                .findById(id)
                .orElseThrow(RuntimeException::new);

        tareaFromDB.setNombre(tarea.getNombre());
        tareaFromDB.setCompletado(tarea.isCompletado());

        return tareaRepositary.save(tareaFromDB);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping ("{id}")
    void delete (@PathVariable  String id) {
            Tarea tarea = tareaRepositary
                    .findById(id)
                    .orElseThrow(RuntimeException::new);
            tareaRepositary.delete(tarea);
        }
}
