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
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");
    }

    public BetaalmethodeCrypto findById(Integer id) {
        BetaalmethodeCrypto betaalmethodeCrypto = null;
        try {
            betaalmethodeCrypto = entityManager.find(BetaalmethodeCrypto.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return betaalmethodeCrypto;

    }
}
