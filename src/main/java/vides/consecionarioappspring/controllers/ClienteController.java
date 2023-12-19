package vides.consecionarioappspring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vides.consecionarioappspring.entities.Cliente;
import vides.consecionarioappspring.exception.RecursoNoEncontradoException;
import vides.consecionarioappspring.services.cliente.ClienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("consecionario-app")
@CrossOrigin(value = "http://localhost:3000")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        var clientes = clienteService.listarClientes();
        clientes.forEach(cliente -> logger.info(cliente.toString()));
        return clientes;
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> guardarClientes(@RequestBody Cliente cliente){
        logger.info("Cliente a agregar: " + cliente);
        return ResponseEntity.ok(clienteService.registrarCliente(cliente));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null)
            throw new RecursoNoEncontradoException("No existe el id: " + id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteRecibido){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null)
            throw new RecursoNoEncontradoException("El id No existe: " + id);
        cliente.setCedula(clienteRecibido.getCedula());
        cliente.setNombres(clienteRecibido.getNombres());
        cliente.setApellidos(clienteRecibido.getApellidos());
        cliente.setTelefono(clienteRecibido.getTelefono());
        cliente.setCorreo(clienteRecibido.getCorreo());
        cliente.setCotizaciones(clienteRecibido.getCotizaciones());
        cliente.setPreferenciaContacto(clienteRecibido.getPreferenciaContacto());
        clienteService.registrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null)
            throw new RecursoNoEncontradoException("El id no existe: " + id);
        clienteService.eliminarCliente(cliente);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
