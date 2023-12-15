package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Cotizacion;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
}
