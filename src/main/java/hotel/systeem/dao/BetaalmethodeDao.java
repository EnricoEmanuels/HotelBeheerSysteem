package hotel.systeem.dao;


import hotel.systeem.entities.Betaalmethode;
import hotel.systeem.entities.Kamer;
import hotel.systeem.entities.Klant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import hotel.systeem.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class BetaalmethodeDao implements DAO<Betaalmethode> {
    private EntityManager entityManager;

    public BetaalmethodeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Betaalmethode> findAll() {
        List<Betaalmethode> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT b FROM Betaalmethode b").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Informatie succesvol opgehaald");
        return result;
    }

    @Override
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

    @Override
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

    @Override
    public void update(Betaalmethode betaalmethode) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(betaalmethode); // Update de klant
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // error printen als het in die catch komt
        }
        System.out.println("Succesvol gewijzigd");
    }

    @Override
    public void deleteById(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Betaalmethode betaalmethode = entityManager.find(Betaalmethode.class, id); // Zoek de klant via ID
            if (betaalmethode != null) {
                entityManager.remove(betaalmethode); // Verwijder de klant als het bestaat
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // als er een error voorkomt gooien in die catch blok
        }
        System.out.println("Succesvol verwijderd");
    }

}
