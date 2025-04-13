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
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");
    }

    public BeschikbareKamer findById(Integer id) {
        BeschikbareKamer beschikbareKamer = null;
        try {
            beschikbareKamer = entityManager.find(BeschikbareKamer.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return beschikbareKamer;

    }
}
