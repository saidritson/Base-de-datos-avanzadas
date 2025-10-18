/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */
import Entities.Cliente;
import java.util.List;

public interface IClienteDAO {
    Cliente crear(Cliente c);
    Cliente buscarPorId(Long id);
    List<Cliente> listar(int limit);
    Cliente actualizar(Cliente c);
    void eliminar(Long id);
}