/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author saidr
 */
import entidad.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProductoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TiendaPU");

    public void insertar(Producto p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizar(Producto p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Producto p = em.find(Producto.class, id);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Producto buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        Producto p = em.find(Producto.class, id);
        em.close();
        return p;
    }

    public List<Producto> listar() {
        EntityManager em = emf.createEntityManager();
        List<Producto> lista = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        em.close();
        return lista;
    }
}