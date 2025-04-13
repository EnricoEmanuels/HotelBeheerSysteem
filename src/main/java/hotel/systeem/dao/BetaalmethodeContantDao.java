package hotel.systeem.dao;

import hotel.systeem.entities.BetaalmethodeContant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BetaalmethodeContantDao {
    private EntityManager entityManager;

    public BetaalmethodeContantDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(BetaalmethodeContant betaalmethodeContant) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(betaalmethodeContant);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");
    }

    public BetaalmethodeContant findById(Integer id) {
        BetaalmethodeContant betaalmethodeContant = null;
        try {
            betaalmethodeContant = entityManager.find(BetaalmethodeContant.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return betaalmethodeContant;

    }
}
