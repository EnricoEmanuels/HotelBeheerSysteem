package hotel.systeem.dao;

import hotel.systeem.entities.Klant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class KlantDao {
    private EntityManager entityManager;

    public KlantDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Klant klant) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(klant);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Succesvol ingevoegd");

    }
}
