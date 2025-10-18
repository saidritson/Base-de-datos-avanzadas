/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */
import Entities.PedidoDetalle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import util.JpaUtil;

public class PedidoDetalleDAO implements IPedidoDetalleDAO {

    private final EntityManager em = JpaUtil.getEntityManager();

    @Override
    public void crear(PedidoDetalle detalle) {
        try {
            em.getTransaction().begin();
            em.persist(detalle);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public PedidoDetalle buscarPorId(Long id) {
        return em.find(PedidoDetalle.class, id);
    }

    @Override
    public List<PedidoDetalle> listar(int limit) {
        if (limit > 100) limit = 100;
        TypedQuery<PedidoDetalle> query = em.createQuery("SELECT d FROM PedidoDetalle d", PedidoDetalle.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public void actualizar(PedidoDetalle detalle) {
        try {
            em.getTransaction().begin();
            em.merge(detalle);
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
            PedidoDetalle detalle = em.find(PedidoDetalle.class, id);
            if (detalle != null) {
                em.remove(detalle);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


}