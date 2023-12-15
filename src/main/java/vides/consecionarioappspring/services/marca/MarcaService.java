package vides.consecionarioappspring.services.marca;

import vides.consecionarioappspring.entities.Marca;

import java.util.List;

public interface MarcaService {

    public List<Marca> listarMarcas();
    public Marca registrarMarca(Marca marca);
    public Marca findById(Long id);
    public void eliminarMarca(Marca marca);

}
