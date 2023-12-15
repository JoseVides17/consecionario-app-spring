package vides.consecionarioappspring.services.venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Venta;
import vides.consecionarioappspring.repositories.VentaRepository;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta registrarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta findById(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarVenta(Venta venta) {
        Venta ventaEncontrada = ventaRepository.findById(venta.getIdVenta()).orElse(null);
        if (ventaEncontrada != null)
            ventaRepository.delete(venta);
    }
}
