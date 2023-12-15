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
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;
    private String modeloCarroceria;
    private String tipoVelocidad;
    private Double precio;
    private String traccion;
    private String tecnologia;
    private String tipoDireccion;
    private String tipoSuspencion;
    private String frenoDelantero;
    private String frenoTrasero;
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "linea_id")
    private Linea linea;

    @ManyToOne
    @JoinColumn(name = "motor_id")
    private Motor motor;
    @ManyToMany(mappedBy = "carros")
    private Set<Venta> ventas;

}
