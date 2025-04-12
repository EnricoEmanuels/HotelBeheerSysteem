package hotel.systeem.dao;

import hotel.systeem.entities.BeschikbareKamer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BeschikbareKamerDao {
    private EntityManager entityManager;

    public BeschikbareKamerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(BeschikbareKamer beschikbareKamer) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(beschikbareKamer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Succesvol ingevoegd");

    }
}
