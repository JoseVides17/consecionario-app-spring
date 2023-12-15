package vides.consecionarioappspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//@CrossOrigin
@ToString
public class Motor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMotor;
    private String marcaMotor;
    private String combustible;
    private String tipo;
    private String potencia;
    private String torque;
    @OneToMany(mappedBy = "motor")
    private Set<Carro> carros;

}
