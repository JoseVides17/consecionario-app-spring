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
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLinea;
    private String tipo;
    private String nombre;
    @ManyToMany(mappedBy = "lineas")
    private Set<Marca> marcas;
    @OneToMany(mappedBy = "linea")
    private Set<Carro> carros;

}
