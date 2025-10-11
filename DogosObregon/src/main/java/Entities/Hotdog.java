/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
@Entity
public class Hotdog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    
    private BigDecimal precio;
    
    private BigDecimal IVA;
    
    
    @OneToMany (mappedBy = "hotdog")
    private Set<PedidoDetalle> detalle;

    public Hotdog() {
    }

    public Hotdog(Long id, String nombre, BigDecimal precio, BigDecimal IVA, Set<PedidoDetalle> detalle) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.IVA = IVA;
        this.detalle = detalle;
    }
    
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getIVA() {
        return IVA;
    }

    public void setIVA(BigDecimal IVA) {
        this.IVA = IVA;
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
