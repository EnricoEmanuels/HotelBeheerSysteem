package hotel.systeem.dao;


import hotel.systeem.entities.Betaalmethode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BetaalmethodeDao {
    private EntityManager entityManager;

    public BetaalmethodeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Betaalmethode betaalmethode) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(betaalmethode);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        System.out.println("Succesvol ingevoegd");

    }
}
