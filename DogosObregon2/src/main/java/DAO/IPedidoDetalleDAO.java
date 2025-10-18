/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */


import Entities.PedidoDetalle;
import java.util.List;

public interface IPedidoDetalleDAO {

    void crear(PedidoDetalle detalle);

    PedidoDetalle buscarPorId(Long id);

    List<PedidoDetalle> listar(int limit);

    void actualizar(PedidoDetalle detalle);

    void eliminar(Long id);


}
