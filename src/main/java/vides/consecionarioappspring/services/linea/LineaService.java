package vides.consecionarioappspring.services.linea;

import vides.consecionarioappspring.entities.Linea;
import vides.consecionarioappspring.entities.Marca;

import javax.sound.sampled.Line;
import java.util.List;

public interface LineaService {

    public List<Linea> listarLineas();
    public Linea registrarLinea(Linea linea);
    public Linea findById(Long id);
    public void eliminarLinea(Linea linea);

}
