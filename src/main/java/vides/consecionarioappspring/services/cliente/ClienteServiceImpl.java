package vides.consecionarioappspring.services.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Cliente;
import vides.consecionarioappspring.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        Cliente clienteEncontrado = clienteRepository.findById(cliente.getCedula()).orElse(null);
        if (clienteEncontrado != null)
            clienteRepository.delete(cliente);
    }
}
