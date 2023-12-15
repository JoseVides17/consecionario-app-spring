package vides.consecionarioappspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vides.consecionarioappspring.entities.Motor;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {
}
