package vides.consecionarioappspring.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Cliente;
import vides.consecionarioappspring.entities.Cotizacion;
import vides.consecionarioappspring.entities.Vendedor;
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

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/cotizaciones")
    public List<Cotizacion> listarCotizaciones(){
        var cotizaciones = cotizacionService.listarCotizaciones();
        cotizaciones.forEach(cotizacion -> logger.info(cotizacion.toString()));
        return cotizaciones;
    }

    @PostMapping("/cotizaciones")
    @Transactional
    public Cotizacion guardarCotizacion(@RequestBody Cotizacion cotizacion){
        Cliente cliente = entityManager.find(Cliente.class, cotizacion.getCliente().getNumeroCliente());
        Vendedor vendedor = entityManager.find(Vendedor.class, cotizacion.getVendedor().getNumeroVendedor());
        if (cliente == null || vendedor == null)
            throw new EntityNotFoundException("Cliente o vendedor inexistente!");
        cotizacion.setVendedor(vendedor);
        cotizacion.setCliente(cliente);
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
