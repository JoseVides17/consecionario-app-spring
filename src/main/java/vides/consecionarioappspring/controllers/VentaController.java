package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Venta;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.venta.VentaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class VentaController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private VentaService ventaService;

    @GetMapping("/ventas")
    public List<Venta> listarVentas(){
        var ventas = ventaService.listarVentas();
        ventas.forEach(vendedor -> logger.info(vendedor.toString()));
        return ventas;
    }

    @PostMapping("/ventas")
    public Venta guardarVendedor(@RequestBody Venta venta){
        logger.info("Venta a agregar: " + venta);
        return ventaService.registrarVenta(venta);
    }

    @GetMapping("/ventas/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Long id){
        Venta venta = ventaService.findById(id);
        if (venta == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(venta);
    }

    @PutMapping("/ventas/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta ventaRecibida){
        Venta venta = ventaService.findById(id);
        if (venta == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        venta.setFechaVenta(ventaRecibida.getFechaVenta());
        venta.setValorVenta(ventaRecibida.getValorVenta());
        venta.setCarros(ventaRecibida.getCarros());
        venta.setCliente(ventaRecibida.getCliente());
        venta.setVendedor(ventaRecibida.getVendedor());
        return ResponseEntity.ok(venta);
    }

    @DeleteMapping("/ventas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarVenta(@PathVariable Long id){
        Venta venta = ventaService.findById(id);
        if (venta == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        ventaService.eliminarVenta(venta);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
