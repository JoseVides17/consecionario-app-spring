package vides.consecionarioappspring.services.vendedor;

import vides.consecionarioappspring.entities.Vendedor;

import java.util.List;

public interface VendedorService {

    public List<Vendedor> listarVendedores();
    public Vendedor registrarVendedor(Vendedor vendedor);
    public Vendedor findById(Long id);
    public void eliminarVendedor(Vendedor vendedor);

}
