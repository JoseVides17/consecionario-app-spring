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
public class Vendedor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroVendedor;
    @Id
    private Long identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    @OneToMany(mappedBy = "vendedor")
    private Set<Cotizacion> cotizaciones;
    @OneToMany(mappedBy = "vendedor")
    private Set<Venta> ventas;

}
