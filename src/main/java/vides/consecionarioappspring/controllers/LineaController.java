package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Linea;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.linea.LineaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class LineaController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);
    @Autowired
    private LineaService lineaService;

    @GetMapping("/lineas")
    public List<Linea> listarLineas(){
        var lineas = lineaService.listarLineas();
        lineas.forEach(linea -> logger.info(linea.toString()));
        return lineas;
    }

    @PostMapping("/lineas")
    public Linea guardarLinea(@RequestBody Linea linea){
        logger.info("Linea a agregar: " + linea);
        return lineaService.registrarLinea(linea);
    }

    @GetMapping("/lineas/{id}")
    public ResponseEntity<Linea> getById(@PathVariable Long id){
        Linea linea = lineaService.findById(id);
        if (linea == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(linea);
    }

    @PutMapping("/lineas/{id}")
    public ResponseEntity<Linea> actualizarLinea(@PathVariable Long id, @RequestBody Linea lineaRecibida){
        Linea linea = lineaService.findById(id);
        if (linea == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        linea.setNombre(lineaRecibida.getNombre());
        linea.setCarros(lineaRecibida.getCarros());
        linea.setTipo(lineaRecibida.getTipo());
        linea.setMarcas(lineaRecibida.getMarcas());
        lineaService.registrarLinea(linea);
        return ResponseEntity.ok(linea);
    }

    @DeleteMapping("/lineas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarLinea(@PathVariable Long id){
        Linea linea = lineaService.findById(id);
        if (linea == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        lineaService.eliminarLinea(linea);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
