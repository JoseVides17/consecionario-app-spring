package vides.consecionarioappspring.services.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Carro;
import vides.consecionarioappspring.repositories.CarroRepository;

import java.util.List;

@Service
public class CarroServiceImpl implements CarroService{

    @Autowired
    private CarroRepository carroRepository;
    @Override
    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    @Override
    public Carro registrarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    @Override
    public Carro findById(Long id) {
        return carroRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCarro(Carro carro) {
        Carro carroEncontrado = carroRepository.findById(carro.getIdCarro()).orElse(null);
        if (carroEncontrado != null)
            carroRepository.delete(carro);
    }
}
