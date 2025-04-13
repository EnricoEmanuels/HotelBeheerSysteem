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
            e.printStackTrace(); // deze code gaat je jouw error wijzen als het in die catch komt
        }
        System.out.println("Succesvol ingevoegd");

    }

    public void deleteById(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Klant klant = entityManager.find(Klant.class, id); // Zoek de klant via ID
            if (klant != null) {
                entityManager.remove(klant); // Verwijder de klant als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol verwijderd");
    }

    public Klant findById(Integer id) {
        Klant klant = null;
        try {
            klant = entityManager.find(Klant.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return klant;

    }
}
