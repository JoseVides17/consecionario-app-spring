package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Cotizacion;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.cotizacion.CotizacionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class CotizacionController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping("/cotizaciones")
    public List<Cotizacion> listarCotizaciones(){
        var cotizaciones = cotizacionService.listarCotizaciones();
        cotizaciones.forEach(cotizacion -> logger.info(cotizacion.toString()));
        return cotizaciones;
    }

    @PostMapping("/cotizaciones")
    public Cotizacion guardarCotizaciones(@RequestBody Cotizacion cotizacion){
        logger.info("Cotizacion a agregar: " + cotizacion);
        return cotizacionService.registrarCotizacion(cotizacion);
    }

    @GetMapping("/cotizaciones/{id}")
    public ResponseEntity<Cotizacion> getById(@PathVariable Long id){
        Cotizacion cotizacion = cotizacionService.findById(id);
        if (cotizacion == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(cotizacion);
    }

    @PutMapping("/cotizaciones/{id}")
    public ResponseEntity<Cotizacion> actualizarCotizacion(@PathVariable Long id, @RequestBody Cotizacion cotizacionRecibida){
        Cotizacion cotizacion = cotizacionService.findById(id);
        if (cotizacion == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        cotizacion.setDetalles(cotizacionRecibida.getDetalles());
        cotizacion.setFecha(cotizacionRecibida.getFecha());
        cotizacion.setValor(cotizacionRecibida.getValor());
        cotizacion.setCliente(cotizacionRecibida.getCliente());
        cotizacion.setVendedor(cotizacionRecibida.getVendedor());
        cotizacionService.registrarCotizacion(cotizacion);
        return ResponseEntity.ok(cotizacion);
    }

    @DeleteMapping("/cotizaciones/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCotizacion(@PathVariable Long id){
        Cotizacion cotizacion = cotizacionService.findById(id);
        if (cotizacion == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        cotizacionService.eliminarCotizacion(cotizacion);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
