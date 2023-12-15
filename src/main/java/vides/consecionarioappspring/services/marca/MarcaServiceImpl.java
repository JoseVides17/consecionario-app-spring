package vides.consecionarioappspring.services.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Marca;
import vides.consecionarioappspring.repositories.MarcaRepository;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService{

    @Autowired
    private MarcaRepository marcaRepository;
    @Override
    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca registrarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public Marca findById(Long id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        return marca;
    }

    @Override
    public void eliminarMarca(Marca marca) {
        Marca marcaEncontrada = marcaRepository.findById(marca.getIdMarca()).orElse(null);
        if (marcaEncontrada != null)
            marcaRepository.delete(marca);
    }
}
