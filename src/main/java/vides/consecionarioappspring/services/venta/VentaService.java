package vides.consecionarioappspring.services.venta;

import vides.consecionarioappspring.entities.Vendedor;
import vides.consecionarioappspring.entities.Venta;

import java.util.List;

public interface VentaService {

    public List<Venta> listarVentas();
    public Venta registrarVenta(Venta venta);
    public Venta findById(Long id);
    public void eliminarVenta(Venta venta);

}
