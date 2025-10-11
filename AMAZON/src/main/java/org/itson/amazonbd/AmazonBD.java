/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.amazonbd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author saidr
 */
public class AmazonBD {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AmazonPU");
        EntityManager em = emf.createEntityManager();
    }
}
