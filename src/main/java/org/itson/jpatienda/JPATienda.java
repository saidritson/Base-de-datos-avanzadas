/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.jpatienda;

/**
 *
 * @author saidr
 */
import dao.ProductoDAO;
import entidad.Producto;

public class JPATienda {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        // Insertar productos
        dao.insertar(new Producto("Laptop", 15000.0));
        dao.insertar(new Producto("Mouse", 300.0));
        dao.insertar(new Producto("Teclado", 500.0));

        System.out.println("Lista inicial:");
        dao.listar().forEach(System.out::println);

        // Modificar un producto
        Producto p = dao.buscar(1L);
        if (p != null) {
            p.setPrecio(14000.0);
            dao.actualizar(p);
        }

        System.out.println("\nDespués de modificar:");
        dao.listar().forEach(System.out::println);

        // Eliminar un producto
        dao.eliminar(2L);

        System.out.println("\nDespués de eliminar:");
        dao.listar().forEach(System.out::println);
    }
}

