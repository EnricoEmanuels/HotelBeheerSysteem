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
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");

    }

    public Betaalmethode findById(Integer id) {
        Betaalmethode betaalmethode = null;
        try {
            betaalmethode = entityManager.find(Betaalmethode.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return betaalmethode;

    }
}
