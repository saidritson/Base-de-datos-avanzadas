/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */
import DAO.IPedidoDAO;
import Entities.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import util.JpaUtil;

public class PedidoDAO implements IPedidoDAO {

    private final EntityManager em = JpaUtil.getEntityManager();

    @Override
    public void crear(Pedido pedido) {
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    @Override
    public List<Pedido> listar(int limit) {
        if (limit > 100) limit = 100;
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public void actualizar(Pedido pedido) {
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, id);
            if (pedido != null) {
                em.remove(pedido);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


}