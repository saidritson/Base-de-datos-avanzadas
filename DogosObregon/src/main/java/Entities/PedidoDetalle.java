/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
@Entity
public class PedidoDetalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detalle")
    private Pedido detalle;

    @ManyToOne
    @JoinColumn(name = "id_hotdog")
    private Hotdog hotdog;

    private int cantidad;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    
    
    private BigDecimal subtotal;

    public PedidoDetalle(Long id, Pedido detalle, Hotdog hotdog, int cantidad, BigDecimal precioVenta, BigDecimal subtotal) {
        this.id = id;
        this.detalle = detalle;
        this.hotdog = hotdog;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.subtotal = subtotal;
    }

    public PedidoDetalle() {
    }

    public Pedido getDetalle() {
        return detalle;
    }

    public void setDetalle(Pedido detalle) {
        this.detalle = detalle;
    }

    

    public Hotdog getHotdog() {
        return hotdog;
    }

    public void setHotdog(Hotdog hotdog) {
        this.hotdog = hotdog;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
