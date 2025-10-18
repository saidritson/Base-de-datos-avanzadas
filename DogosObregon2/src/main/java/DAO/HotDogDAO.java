/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author saidr
 */
import Entities.Hotdog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import util.JpaUtil;

public class HotDogDAO implements IHotDogDAO {

    @Override
    public Hotdog crear(Hotdog h) {
        if (h.getPrecio() == null || h.getPrecio().doubleValue() <= 0) {
            throw new IllegalArgumentException("Precio debe ser > 0");
        }
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(h);
            tx.commit();
            return h;
        } catch (Exception ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Hotdog buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Hotdog.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hotdog> listar(int limit) {
        if (limit <= 0 || limit > 100) limit = 100;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Hotdog> q = em.createQuery("SELECT h FROM hotdog h", Hotdog.class);
            q.setMaxResults(limit);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Hotdog actualizar(Hotdog h) {
        if (h.getPrecio() == null || h.getPrecio().doubleValue() <= 0) {
            throw new IllegalArgumentException("Precio debe ser > 0");
        }
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Hotdog merged = em.merge(h);
            tx.commit();
            return merged;
        } catch (Exception ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Hotdog h = em.find(Hotdog.class, id);
            if (h != null) em.remove(h);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
