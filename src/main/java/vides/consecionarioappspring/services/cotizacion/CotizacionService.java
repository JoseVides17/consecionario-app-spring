package vides.consecionarioappspring.services.cotizacion;

import vides.consecionarioappspring.entities.Cotizacion;
import vides.consecionarioappspring.entities.Marca;

import java.util.List;

public interface CotizacionService {

    public List<Cotizacion> listarCotizaciones();
    public Cotizacion registrarCotizacion(Cotizacion cotizacion);
    public Cotizacion findById(Long id);
    public void eliminarCotizacion(Cotizacion cotizacion);

}
