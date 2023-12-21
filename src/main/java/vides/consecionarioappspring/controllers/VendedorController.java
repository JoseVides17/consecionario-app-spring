package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Vendedor;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.vendedor.VendedorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class VendedorController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/vendedores")
    public List<Vendedor> listarVendedores(){
        var vendedores = vendedorService.listarVendedores();
        vendedores.forEach(vendedor -> logger.info(vendedor.toString()));
        return vendedores;
    }

    @PostMapping("/vendedores")
    public Vendedor guardarVendedor(@RequestBody Vendedor vendedor){
        logger.info("Vendedor a agregar: " + vendedor);
        return vendedorService.registrarVendedor(vendedor);
    }

    @GetMapping("/vendedores/{id}")
    public ResponseEntity<Vendedor> getById(@PathVariable Long id){
        Vendedor vendedor = vendedorService.findById(id);
        if (vendedor == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(vendedor);
    }

    @PutMapping("/vendedores/{id}")
    public ResponseEntity<Vendedor> actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedorRecibido){
        Vendedor vendedor = vendedorService.findById(id);
        if (vendedor == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        vendedor.setIdentificacion(vendedorRecibido.getIdentificacion());
        vendedor.setNombres(vendedorRecibido.getNombres());
        vendedor.setApellidos(vendedorRecibido.getApellidos());
        vendedor.setTelefono(vendedorRecibido.getTelefono());
        vendedor.setDireccion(vendedorRecibido.getDireccion());
        vendedor.setVentas(vendedorRecibido.getVentas());
        vendedor.setCotizaciones(vendedorRecibido.getCotizaciones());
        vendedorService.registrarVendedor(vendedor);
        return ResponseEntity.ok(vendedor);
    }

    @DeleteMapping("/vendedores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarVendedor(@PathVariable Long id){
        Vendedor vendedor = vendedorService.findById(id);
        if (vendedor == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        vendedorService.eliminarVendedor(vendedor);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
