/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */
import Entities.Pedido;
import java.util.List;

public interface IPedidoDAO {

    void crear(Pedido pedido);

    Pedido buscarPorId(Long id);

    List<Pedido> listar(int limit);

    void actualizar(Pedido pedido);

    void eliminar(Long id);


}

