package vides.consecionarioappspring.services.cotizacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Cotizacion;
import vides.consecionarioappspring.repositories.CotizacionRepository;

import java.util.List;

@Service
public class CotizacionServiceImpl implements CotizacionService{

    @Autowired
    private CotizacionRepository cotizacionRepository;
    @Override
    public List<Cotizacion> listarCotizaciones() {
        return cotizacionRepository.findAll();
    }

    @Override
    public Cotizacion registrarCotizacion(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }

    @Override
    public Cotizacion findById(Long id) {
        return cotizacionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCotizacion(Cotizacion cotizacion) {
        Cotizacion cotizacionEncontrada = cotizacionRepository.findById(cotizacion.getNumero()).orElse(null);
        if (cotizacionEncontrada != null)
            cotizacionRepository.delete(cotizacion);
    }
}
