package hotel.systeem.dao;

import hotel.systeem.entities.KamersBoeken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class KamersBoekenDao {
    private EntityManager entityManager;

    public KamersBoekenDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(KamersBoeken kamersBoeken) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(kamersBoeken);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Succesvol ingevoegd");

    }
}
