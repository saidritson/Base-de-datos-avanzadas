/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Said Chavez
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "met_pgo", nullable = false)
    private MetodoPago metodoPago;

    
    @OneToMany(mappedBy = "detalle", cascade = CascadeType.REMOVE)
    private Set<PedidoDetalle> detalle;

    public Pedido(Long id, Cliente cliente, LocalDateTime fecha, MetodoPago metodoPago, Set<PedidoDetalle> detalle) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.detalle = detalle;
    }

    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Set<PedidoDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<PedidoDetalle> detalle) {
        this.detalle = detalle;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
