package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
