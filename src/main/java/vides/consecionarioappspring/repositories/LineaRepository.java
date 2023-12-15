package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Linea;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {
}
