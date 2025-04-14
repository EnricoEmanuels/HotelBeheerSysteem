package hotel.systeem.dao;

import hotel.systeem.entities.BeschikbareKamer;
import hotel.systeem.entities.Kamer;
import hotel.systeem.entities.KamersBoeken;
import hotel.systeem.entities.Klant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import hotel.systeem.interfaces.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KlantDao implements DAO<Klant> {
    private EntityManager entityManager;

    public KlantDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Klant> findAll() {
        List<Klant> result = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            result = entityManager.createQuery("SELECT k FROM Klant k").getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Informatie succesvol opgehaald");
        return result;
    }

    @Override
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

    @Override
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
            e.printStackTrace(); // als er een error voorkomt gooien in die catch blok
        }
        System.out.println("Succesvol verwijderd");
    }


    @Override
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

    @Override
    public void update(Klant klant) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(klant); // Update de klant
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // error printen als het in die catch komt
        }
        System.out.println("Succesvol gewijzigd");
    }

    public void opwaarderen(Integer id ,Double usdOpwaarderen) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Klant klant = entityManager.find(Klant.class, id);

            if (klant == null) {
                System.out.println("klant ID: " + id + " niet gevonden");
            } else {
                System.out.println("Klant ID: " + id + " wel gevonden");
                double huidigeBalans = klant.getBalans(); // die variabel klant heeft toegang tot al die eigenschappen en methodes avn die klass
                double nieuwBalans = huidigeBalans + usdOpwaarderen;
                klant.setBalans(nieuwBalans);
                entityManager.merge(klant); // vergeet deze niet, anders wordt het niet opgeslagen
                System.out.println("Uw saldo na het opwaarderen: " + klant.getBalans());

            }
        transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        System.out.println("Opwaarderen proces afgerond");
    }

    // je gaat met je id boeken

//    public void kamerboeken(Integer id, BeschikbareKamer beschikbareKamer) {
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try {
//            transaction.begin();
//            Klant klant = entityManager.find(Klant.class, id);
//            if (klant == null) {
//                System.out.println("klant ID: " + id + " niet gevonden");
//            } else {
//                System.out.println("Klant ID: " + id + " wel gevonden");
//
//                // ik wil die beschikbare class bestellen
//
//                // maar die beschibare class heeft een id in wil in die id van die kamer gaan om die prijs te halen
//                // en die prij af te trekken met die  balans die klant heeft en vervlgens
//                // wil ik dat die beschikbare kamer boeken mar het moet dan verwijderdt worden in de Kamerdao
//                // want ik heb het al gekocht als alles successvol is
//                // maar als mij balans te laag is moet het ook zeggen dat ik het niet kan kopen
//            }
//
//        } catch (Exception e) {
//            transaction.rollback();
//            e.printStackTrace();
//        }
//        System.out.println("Kamerboeken proces afgerond");
//    }

    public void kamerboeken(Integer klantId, BeschikbareKamer beschikbareKamer) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // 1. Vind de klant
            Klant klant = entityManager.find(Klant.class, klantId);
            if (klant == null) {
                System.out.println("Klant met ID " + klantId + " niet gevonden.");
                return;
            }

            // 2. Vind de beschikbare kamer
            if (beschikbareKamer == null) {
                System.out.println("Beschikbare kamer met ID " + beschikbareKamer + " niet gevonden.");
                return;
            }

            // 3. Haal de echte kamer op via kamerId van BeschikbareKamer
            Kamer kamer = entityManager.find(Kamer.class, beschikbareKamer.getKamer().getId());
            if (kamer == null) {
                System.out.println("Kamer niet gevonden.");
                return;
            }

            // 4. Check of klant genoeg geld heeft
            if (klant.getBalans() < kamer.getPrijsPerMaand()) {
                System.out.println("Onvoldoende saldo. Klant heeft: $" + klant.getBalans() + ", Kamer kost: $" + kamer.getPrijsPerMaand());
                return;
            }

            // 5. Trek prijs af van klantbalans
            double nieuweBalans = klant.getBalans() - kamer.getPrijsPerMaand();
            klant.setBalans(nieuweBalans);
            entityManager.merge(klant);

            // 6. Maak een boeking aan
            KamersBoeken boeking = new KamersBoeken();
            boeking.setKlant(klant);
            boeking.setBeschikbareKamer(beschikbareKamer);

//            Date vandaag = new Date();
//            Calendar c = Calendar.getInstance();
//            c.setTime(vandaag);
//            c.add(Calendar.MONTH, 1);
//            Date eindDatum = c.getTime();
//
//            boeking.setStartDatum(vandaag);
//            boeking.setEindDatum(eindDatum);

            boeking.setBetaald("Creditcaard");
            entityManager.persist(boeking);

            // 7. Verwijder de kamer uit de lijst van beschikbare kamers
            entityManager.remove(beschikbareKamer);

            transaction.commit();

            System.out.println("Kamer succesvol geboekt door klant ID: " + klantId);
            System.out.println("Nieuwe balans: $" + klant.getBalans());
//            System.out.println("Boeking geldig tot: " + eindDatum);

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            System.out.println("Kamerboeken mislukt.");
        }
    }




}
