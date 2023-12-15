package vides.consecionarioappspring.services.motor;

import vides.consecionarioappspring.entities.Marca;
import vides.consecionarioappspring.entities.Motor;

import java.util.List;

public interface MotorService {

    public List<Motor> listarMotores();
    public Motor registrarMotor(Motor motor);
    public Motor findById(Long id);
    public void eliminarMotor(Motor motor);

}
