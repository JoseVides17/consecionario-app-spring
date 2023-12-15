package vides.consecionarioappspring.services.motor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vides.consecionarioappspring.entities.Motor;
import vides.consecionarioappspring.repositories.MotorRepository;

import java.util.List;

@Service
public class MotorServiceImpl implements MotorService{

    @Autowired
    private MotorRepository motorRepository;
    @Override
    public List<Motor> listarMotores() {
        return motorRepository.findAll();
    }

    @Override
    public Motor registrarMotor(Motor motor) {
        return motorRepository.save(motor);
    }

    @Override
    public Motor findById(Long id) {
        return motorRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarMotor(Motor motor) {
        Motor motorEncontrado = motorRepository.findById(motor.getIdMotor()).orElse(null);
        if (motorEncontrado != null)
            motorRepository.delete(motor);
    }
}
