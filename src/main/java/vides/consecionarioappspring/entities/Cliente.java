package vides.consecionarioappspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//@CrossOrigin
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCliente;
    private Long cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String preferenciaContacto;
    @OneToMany(mappedBy = "cliente")
    private Set<Cotizacion> cotizaciones;
    @OneToMany(mappedBy = "cliente")
    private Set<Venta> ventas;


}
