package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
