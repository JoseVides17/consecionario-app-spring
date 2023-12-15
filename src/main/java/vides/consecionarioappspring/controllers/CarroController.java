package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Carro;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.carro.CarroService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class CarroController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private CarroService carroService;

    @GetMapping("/carros")
    public List<Carro> listarCarros(){
        var carros = carroService.listarCarros();
        carros.forEach(carro -> logger.info(carro.toString()));
        return carros;
    }

    @PostMapping("/carros")
    public Carro guardarCarros(@RequestBody Carro carro){
        logger.info("Carro a agregar: " + carro);
        return carroService.registrarCarro(carro);
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<Carro> getById(@PathVariable Long id){
        Carro carro = carroService.findById(id);
        if (carro == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(carro);
    }

    @PutMapping("/carros/{id}")
    public ResponseEntity<Carro> actualizarCarro(@PathVariable Long id, @RequestBody Carro carroRecibido){
        Carro carro = carroService.findById(id);
        if (carro == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        carro.setModeloCarroceria(carroRecibido.getModeloCarroceria());
        carro.setFrenoDelantero(carroRecibido.getFrenoDelantero());
        carro.setFrenoTrasero(carroRecibido.getFrenoTrasero());
        carro.setTecnologia(carroRecibido.getTecnologia());
        carro.setPrecio(carroRecibido.getPrecio());
        carro.setTipoDireccion(carroRecibido.getTipoDireccion());
        carro.setTipoSuspencion(carroRecibido.getTipoSuspencion());
        carro.setTipoVelocidad(carroRecibido.getTipoVelocidad());
        carro.setTraccion(carroRecibido.getTraccion());
        carro.setLinea(carroRecibido.getLinea());
        carro.setMarca(carroRecibido.getMarca());
        carro.setMotor(carroRecibido.getMotor());
        carro.setVentas(carroRecibido.getVentas());
        carroService.registrarCarro(carro);
        return ResponseEntity.ok(carro);
    }

    @DeleteMapping("/carros/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCarro(@PathVariable Long id){
        Carro carro = carroService.findById(id);
        if (carro == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        carroService.eliminarCarro(carro);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
