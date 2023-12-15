package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Motor;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.motor.MotorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class MotorController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);
    @Autowired
    private MotorService motorService;

    @GetMapping("/motores")
    public List<Motor> listarMotores(){
        var motores = motorService.listarMotores();
        motores.forEach(motor -> logger.info(motor.toString()));
        return motores;
    }

    @PostMapping("/motores")
    public Motor guardarMotor(@RequestBody Motor motor){
        logger.info("Motor a agregar: " + motor);
        return motorService.registrarMotor(motor);
    }

    @GetMapping("/motores/{id}")
    public ResponseEntity<Motor> getById(@PathVariable Long id){
        Motor motor = motorService.findById(id);
        if (motor == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(motor);
    }

    @PutMapping("/motores/{id}")
    public ResponseEntity<Motor> actualizarMotor(@PathVariable Long id, @RequestBody Motor motorRecibido){
        Motor motor = motorService.findById(id);
        if (motor == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        motor.setMarcaMotor(motorRecibido.getMarcaMotor());
        motor.setTipo(motorRecibido.getTipo());
        motor.setCombustible(motorRecibido.getCombustible());
        motor.setPotencia(motorRecibido.getPotencia());
        motor.setTorque(motorRecibido.getTorque());
        motor.setCarros(motorRecibido.getCarros());
        motorService.registrarMotor(motor);
        return ResponseEntity.ok(motor);
    }

    @DeleteMapping("/motores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMotor(@PathVariable Long id){
        Motor motor = motorService.findById(id);
        if (motor == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        motorService.eliminarMotor(motor);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
