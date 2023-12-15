package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
