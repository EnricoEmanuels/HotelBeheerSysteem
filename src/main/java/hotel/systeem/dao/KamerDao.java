package hotel.systeem.dao;

import hotel.systeem.entities.Kamer;
import hotel.systeem.entities.KamersBoeken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class KamerDao {
    private EntityManager entityManager;

    public KamerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Kamer kamer) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(kamer);
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
            Kamer kamer = entityManager.find(Kamer.class, id); // Zoek de klant via ID
            if (kamer != null) {
                entityManager.remove(kamer); // Verwijder de klant als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Succesvol verwijderd");
    }

    public Kamer findById(Integer id) {
        Kamer kamer = null;
        try {
            kamer = entityManager.find(Kamer.class, id); // Zoek de klant via ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Informatie van deze succesvol opgehaald");
        return kamer;

    }
}
