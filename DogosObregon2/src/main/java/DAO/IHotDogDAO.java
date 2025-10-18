/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */
import Entities.Hotdog;
import java.util.List;

public interface IHotDogDAO {
    Hotdog crear(Hotdog h);
    Hotdog buscarPorId(Long id);
    List<Hotdog> listar(int limit);
    Hotdog actualizar(Hotdog h);
    void eliminar(Long id);
}
