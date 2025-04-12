package hotel.systeem.dao;

import hotel.systeem.entities.BetaalmethodeCrypto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BetaalmethodeCryptoDao {
    private EntityManager entityManager;

    public BetaalmethodeCryptoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(BetaalmethodeCrypto betaalmethodeCrypto) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(betaalmethodeCrypto);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Succesvol ingevoegd");

    }
}
