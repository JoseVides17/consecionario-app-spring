package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
