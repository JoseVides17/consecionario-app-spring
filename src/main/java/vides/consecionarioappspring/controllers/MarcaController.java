package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Marca;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.marca.MarcaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class MarcaController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/marcas")
    public List<Marca> listarMarcas(){
        var marcas = marcaService.listarMarcas();
        marcas.forEach(marca -> logger.info(marca.toString()));
        return marcas;
    }

    @PostMapping("/marcas")
    public Marca guardarMarca(@RequestBody Marca marca){
        logger.info("Marca a agregar: " + marca);
        return marcaService.registrarMarca(marca);
    }

    @GetMapping("/marcas/{id}")
    public ResponseEntity<Marca> getById(@PathVariable Long id){
        Marca marca = marcaService.findById(id);
        if (marca == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(marca);
    }

    @PutMapping("/marcas/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Long id, @RequestBody Marca marcaRecibida){
        Marca marca = marcaService.findById(id);
        if (marca == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        marca.setNombre(marcaRecibida.getNombre());
        marca.setCarros(marcaRecibida.getCarros());
        marca.setLineas(marcaRecibida.getLineas());
        marcaService.registrarMarca(marca);
        return ResponseEntity.ok(marca);
    }

    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMarca(@PathVariable Long id){
        Marca marca = marcaService.findById(id);
        if (marca == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        marcaService.eliminarMarca(marca);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
