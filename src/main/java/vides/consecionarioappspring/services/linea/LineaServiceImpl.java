package vides.consecionarioappspring.services.linea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Linea;
import vides.consecionarioappspring.repositories.LineaRepository;

import java.util.List;

@Service
public class LineaServiceImpl implements LineaService{

    @Autowired
    private LineaRepository lineaRepository;
    @Override
    public List<Linea> listarLineas() {
        return lineaRepository.findAll();
    }

    @Override
    public Linea registrarLinea(Linea linea) {
        return lineaRepository.save(linea);
    }

    @Override
    public Linea findById(Long id) {
        return lineaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarLinea(Linea linea) {
        Linea lineaEncontrada = lineaRepository.findById(linea.getIdLinea()).orElse(null);
        if (lineaEncontrada != null)
            lineaRepository.delete(linea);
    }
}
