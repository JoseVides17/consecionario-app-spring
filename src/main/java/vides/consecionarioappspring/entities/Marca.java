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
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarca;
    private String nombre;
    @ManyToMany
    @JoinTable(
            name = "marca_linea",
            joinColumns = @JoinColumn(name = "marca_id"),
            inverseJoinColumns = @JoinColumn(name = "linea_id")
    )
    private Set<Linea> lineas;
    @OneToMany(mappedBy = "marca")
    private Set<Carro> carros;

}
