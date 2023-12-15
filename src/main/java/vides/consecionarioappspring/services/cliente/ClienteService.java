package vides.consecionarioappspring.services.cliente;

import vides.consecionarioappspring.entities.Cliente;
import vides.consecionarioappspring.entities.Marca;

import java.util.List;

public interface ClienteService {

    public List<Cliente> listarClientes();
    public Cliente registrarCliente(Cliente cliente);
    public Cliente findById(Long id);
    public void eliminarCliente(Cliente cliente);

}
