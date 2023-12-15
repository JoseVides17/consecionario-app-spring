package vides.consecionarioappspring.services.vendedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Vendedor;
import vides.consecionarioappspring.repositories.VendedorRepository;

import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService{

    @Autowired
    private VendedorRepository vendedorRepository;
    @Override
    public List<Vendedor> listarVendedores() {
        return vendedorRepository.findAll();
    }

    @Override
    public Vendedor registrarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor findById(Long id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarVendedor(Vendedor vendedor) {
        Vendedor vendedorEncontrado =vendedorRepository.findById(vendedor.getIdentificacion()).orElse(null);
        if (vendedorEncontrado != null)
            vendedorRepository.delete(vendedor);
    }
}
