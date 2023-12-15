package vides.consecionarioappspring.services.carro;

import vides.consecionarioappspring.entities.Carro;
import vides.consecionarioappspring.entities.Linea;

import java.util.List;

public interface CarroService {

    public List<Carro> listarCarros();
    public Carro registrarCarro(Carro carro);
    public Carro findById(Long id);
    public void eliminarCarro(Carro carro);

}
