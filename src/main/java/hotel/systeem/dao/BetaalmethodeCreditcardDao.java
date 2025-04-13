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
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");
    }

    public BetaalmethodeCreditcard findById(Integer id) {
        BetaalmethodeCreditcard betaalmethodeCreditcard = null;
        try {
            betaalmethodeCreditcard = entityManager.find(BetaalmethodeCreditcard.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return betaalmethodeCreditcard;

    }
}
