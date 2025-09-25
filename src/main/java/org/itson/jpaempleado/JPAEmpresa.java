/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.jpaempleado;

import dao.EmpleadoDAO;
import java.time.LocalDate;
import java.util.List;
import model.Empleado;
import model.Empleado.Estatus;

/**
 *
 * @author saidr
 */
public class JPAEmpresa {

    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO();

        // Insertar empleados
        dao.insertar(new Empleado("Ana Perez", "ana@example.com", 25000.0, Estatus.ACTIVO, LocalDate.now()));
        dao.insertar(new Empleado("Luis Gomez", "luis@example.com", 30000.0, Estatus.ACTIVO, LocalDate.now()));

        // Listar todos
        System.out.println("Lista inicial:");
        listarEmpleados(dao.listar());

        // Actualizar un empleado
        Empleado e = dao.buscar(1L);
        if (e != null) {
            e.setNombre("Ana Lopez");
            dao.actualizar(e);
        }
        System.out.println("Después de actualizar:");
        listarEmpleados(dao.listar());

        // Aumentar salario
        dao.aumentarSalario(2L, 10.0);
        System.out.println("Después de aumentar salario:");
        listarEmpleados(dao.listar());

        // Eliminar empleado
        dao.eliminar(1L);
        System.out.println("Después de eliminar:");
        listarEmpleados(dao.listar());
    }

    private static void listarEmpleados(List<Empleado> lista) {
        for (Empleado e : lista) {
            System.out.println(e);
        }
        System.out.println("-----------------------------");
    }
    
}
