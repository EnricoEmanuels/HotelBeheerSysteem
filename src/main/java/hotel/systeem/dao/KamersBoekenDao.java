package hotel.systeem.dao;

import hotel.systeem.entities.KamersBoeken;
import hotel.systeem.entities.Klant;
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
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");

    }

    public KamersBoeken findById(Integer id) {
        KamersBoeken kamersBoeken = null;
        try {
            kamersBoeken = entityManager.find(KamersBoeken.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return kamersBoeken;

    }
}
