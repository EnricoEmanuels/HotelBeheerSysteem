package hotel.systeem.dao;

import hotel.systeem.entities.BetaalmethodeCreditcard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BetaalmethodeCreditcardDao {
    private EntityManager entityManager;

    public BetaalmethodeCreditcardDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(BetaalmethodeCreditcard betaalmethodeCreditcard) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(betaalmethodeCreditcard);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Succesvol ingevoegd");

    }
}
