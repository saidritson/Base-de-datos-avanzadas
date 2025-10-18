/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dogosobregon;

/**
 *
 * @author saidr
 */
import DAO.ClienteDAO;
import DAO.PedidoDAO;
import DAO.PedidoDetalleDAO;
import Entities.Pedido;
import Entities.*;
import jakarta.persistence.EntityManager;
import util.JpaUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class MainPruebas {

    public static void main(String[] args) {
                ClienteDAO clienteDAO = new ClienteDAO();

        EntityManager em = JpaUtil.getEntityManager();

        // ==== CREAR CLIENTES ====
        // Cliente 1
        Cliente c1 = new Cliente(
            null,
            "Juan",
            "Pérez",
            "",
            LocalDate.of(1990, 1, 15),
            null,
            new HashSet<>(Set.of("6621234567")),
            new HashSet<>(Set.of("sin cebolla", "extra queso")),
            new HashSet<>()
        );
        clienteDAO.crear(c1);

        // Cliente 2 — recomendado por Cliente 1
        Cliente c2 = new Cliente(
            null,
            "Ana",
            "Gómez",
            "",
            LocalDate.of(1992, 5, 10),
            c1,
            new HashSet<>(Set.of("6629876543")),
            new HashSet<>(Set.of("sin mostaza", "doble tocino")),
            new HashSet<>()
        );
        clienteDAO.crear(c2);

        // Cliente 3
        Cliente c3 = new Cliente(
            null,
            "Luis",
            "Torres",
            "",
            LocalDate.of(1988, 8, 25),
            null,
            new HashSet<>(Set.of("6621112233")),
            new HashSet<>(Set.of("extra salsa", "sin catsup")),
            new HashSet<>()
        );
        clienteDAO.crear(c3);

        // Cliente 4
        Cliente c4 = new Cliente(
            null,
            "Marta",
            "López",
            "",
            LocalDate.of(1995, 3, 3),
            null,
            new HashSet<>(Set.of("6623334455")),
            new HashSet<>(Set.of("sin picante", "extra mayonesa")),
            new HashSet<>()
        );
        clienteDAO.crear(c4);
  

        // Cliente 5
        Cliente c5 = new Cliente(
            null,
            "Carlos",
            "Ruiz",
            "",
            LocalDate.of(1991, 12, 1),
            null,
            new HashSet<>(Set.of("6627778899")),
            new HashSet<>(Set.of("sin pan", "doble salchicha")),
            new HashSet<>()
        );
        clienteDAO.crear(c5);

        // autorrelación (c1 recomienda a c2)
        c2.setClienteRecomienda(c1);

        em.getTransaction().begin();
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);
        em.persist(c5);
        em.getTransaction().commit();

        System.out.println("✅ Clientes cargados correctamente");
       
        
         Hotdog h1 = new Hotdog(1L, "Clásico", new BigDecimal("45.00"), new BigDecimal("0.16"), new HashSet<>());
        Hotdog h2 = new Hotdog(2L, "Bacon Deluxe", new BigDecimal("60.00"), new BigDecimal("0.16"), new HashSet<>());
        Hotdog h3 = new Hotdog(3L, "Jalapeño Power", new BigDecimal("55.00"), new BigDecimal("0.16"), new HashSet<>());

        em.getTransaction().begin();
        em.persist(h1);
        em.persist(h2);
        em.persist(h3);
        em.getTransaction().commit();

        System.out.println("✅ Hotdogs cargados correctamente");

        // ==== CREAR PREFERENCIAS ====
        em.getTransaction().begin();
        c1.setPreferencias(new HashSet<>(Arrays.asList("sin cebolla", "extra tocino")));
        c2.setPreferencias(new HashSet<>(Arrays.asList("sin mostaza", "con queso")));
        c3.setPreferencias(new HashSet<>(Arrays.asList("sin mayonesa", "extra chile")));
        c4.setPreferencias(new HashSet<>(Arrays.asList("doble salchicha", "sin jitomate")));
        c5.setPreferencias(new HashSet<>(Arrays.asList("extra catsup", "sin pepinillos")));
        em.merge(c1);
        em.merge(c2);
        em.merge(c3);
        em.merge(c4);
        em.merge(c5);
        em.getTransaction().commit();

        System.out.println("✅ Preferencias asignadas correctamente");

        // ==== CREAR PEDIDOS CON DETALLES ====
        PedidoDAO pedidoDAO = new PedidoDAO();
        PedidoDetalleDAO detalleDAO = new PedidoDetalleDAO();

        Random rnd = new Random();
        List<Cliente> clientes = Arrays.asList(c1, c2, c3, c4, c5);
        List<Hotdog> hotdogs = Arrays.asList(h1, h2, h3);

        for (int i = 0; i < 7; i++) {
            Cliente cliente = clientes.get(rnd.nextInt(clientes.size()));
            Pedido pedido = new Pedido(null, cliente, LocalDateTime.now().minusDays(rnd.nextInt(5)),
                    MetodoPago.values()[rnd.nextInt(MetodoPago.values().length)], new HashSet<>());

            pedidoDAO.crear(pedido);

            int numDetalles = 1 + rnd.nextInt(3);
            for (int j = 0; j < numDetalles; j++) {
                Hotdog hotdog = hotdogs.get(rnd.nextInt(hotdogs.size()));
                int cantidad = 1 + rnd.nextInt(3);
                BigDecimal precio = hotdog.getPrecio();
                BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));

                PedidoDetalle detalle = new PedidoDetalle(null, pedido, hotdog, cantidad, precio, subtotal);
                detalleDAO.crear(detalle);
            }
        }

        System.out.println("✅ Pedidos y detalles generados correctamente");

        // ==== CRUD BÁSICO ====
        System.out.println("\n🔹 CRUD Pedido");
        Pedido pedidoEjemplo = pedidoDAO.listar(1).get(0);
        System.out.println("Pedido encontrado: ID = " + pedidoEjemplo.getId());

        pedidoEjemplo.setMetodoPago(MetodoPago.TARJETA);
        pedidoDAO.actualizar(pedidoEjemplo);
        System.out.println("Pedido actualizado a método TARJETA.");

        pedidoDAO.eliminar(pedidoEjemplo.getId());
        System.out.println("Pedido eliminado correctamente.");

        // ==== CONSULTAS EXTRA (10) ====
        System.out.println("\n================== CONSULTAS JPQL ==================");

        // 1. Clientes con mayor gasto total
        List<Object[]> clientesGasto = em.createQuery(
                "SELECT p.cliente.nombre, SUM(d.subtotal) " +
                "FROM PedidoDetalle d JOIN d.detalle p " +
                "GROUP BY p.cliente.nombre ORDER BY SUM(d.subtotal) DESC")
                .getResultList();
        System.out.println("1️⃣ Clientes con mayor gasto total:");
        clientesGasto.forEach(row -> System.out.println(row[0] + ": $" + row[1]));

        // 2. Hotdogs más vendidos
        List<Object[]> masVendidos = em.createQuery(
                "SELECT d.hotdog.nombre, SUM(d.cantidad) " +
                "FROM PedidoDetalle d GROUP BY d.hotdog.nombre ORDER BY SUM(d.cantidad) DESC")
                .getResultList();
        System.out.println("\n2️⃣ Hotdogs más vendidos:");
        masVendidos.forEach(row -> System.out.println(row[0] + ": " + row[1] + " unidades"));

        // 3. Ticket promedio por pedido
        Double promedio = (Double) em.createQuery(
                "SELECT AVG((SELECT SUM(d.subtotal) FROM PedidoDetalle d WHERE d.detalle = p)) FROM Pedido p")
                .getSingleResult();
        System.out.println("\n3️⃣ Ticket promedio por pedido: $" + promedio);

        // 4. Pedidos agrupados por método de pago
        List<Object[]> agrupadosPago = em.createQuery(
                "SELECT p.metodoPago, COUNT(p) FROM Pedido p GROUP BY p.metodoPago")
                .getResultList();
        System.out.println("\n4️⃣ Pedidos agrupados por método de pago:");
        agrupadosPago.forEach(r -> System.out.println(r[0] + ": " + r[1]));

        // 5. Clientes sin compras
        List<Cliente> sinCompras = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.id NOT IN (SELECT p.cliente.id FROM Pedido p)", Cliente.class)
                .getResultList();
        System.out.println("\n5️⃣ Clientes sin compras:");
        sinCompras.forEach(c -> System.out.println(c.getNombre()));

        // 6. Clientes recomendados (autorrelación)
        List<Cliente> recomendados = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.clienteRecomienda IS NOT NULL", Cliente.class)
                .getResultList();
        System.out.println("\n6️⃣ Clientes recomendados:");
        recomendados.forEach(c -> System.out.println(c.getNombre() + " recomendado por " + c.getClienteRecomienda().getNombre()));

        // 7. Búsqueda por preferencia (sin cebolla)
        List<Cliente> sinCebolla = em.createQuery(
                "SELECT c FROM Cliente c WHERE 'sin cebolla' MEMBER OF c.preferencias", Cliente.class)
                .getResultList();
        System.out.println("\n7️⃣ Clientes que prefieren 'sin cebolla':");
        sinCebolla.forEach(c -> System.out.println(c.getNombre()));

        // 8. Hotdogs con precio + IVA (16%)
        List<Object[]> conIVA = em.createQuery(
                "SELECT h.nombre, h.precio * 1.16 FROM Hotdog h").getResultList();
        System.out.println("\n8️⃣ Hotdogs con precio + IVA:");
        conIVA.forEach(h -> System.out.println(h[0] + ": $" + h[1]));

        // 9. Pedidos con más de X artículos (≥2) y total ≥100
        List<Pedido> pedidosGrandes = em.createQuery(
                "SELECT DISTINCT p FROM Pedido p JOIN p.detalle d " +
                "GROUP BY p HAVING SUM(d.cantidad) >= 2 AND SUM(d.subtotal) >= 100", Pedido.class)
                .getResultList();
        System.out.println("\n9️⃣ Pedidos grandes:");
        pedidosGrandes.forEach(p -> System.out.println("Pedido " + p.getId()));

        // 10. Último pedido por cliente
        List<Object[]> ultimos = em.createQuery(
                "SELECT p.cliente.nombre, MAX(p.fecha) FROM Pedido p GROUP BY p.cliente.nombre")
                .getResultList();
        System.out.println("\n🔟 Último pedido por cliente:");
        ultimos.forEach(u -> System.out.println(u[0] + ": " + u[1]));

        System.out.println("\n✅ DEMOSTRACIÓN FINAL COMPLETADA ✅");
    }
}

