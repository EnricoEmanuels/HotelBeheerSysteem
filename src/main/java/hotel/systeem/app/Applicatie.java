package hotel.systeem.app;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import hotel.systeem.config.JPAConfig;
import jakarta.persistence.Persistence;

import hotel.systeem.entities.Klant;
import hotel.systeem.entities.KamersBoeken;
import hotel.systeem.entities.Kamer;
import hotel.systeem.entities.BetaalmethodeCrypto;
import hotel.systeem.entities.BetaalmethodeCreditcard;
import hotel.systeem.entities.BetaalmethodeContant;
import hotel.systeem.entities.Betaalmethode;
import hotel.systeem.entities.BeschikbareKamer;

import java.sql.Date;


public class Applicatie {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = JPAConfig.getEntityMangerFactory();
        EntityManager entityManager = JPAConfig.getEntityManger();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            // bij kamers boeken denk ik dat we als laatse dgeen kamer maar bescikbare kamers moeten plaatsen
            // want bij beschikbare kamers ga ik al kamers toeveoege die beschikbaar zijn
            // extra forein key zetten voor betaalmethode

            Kamer kamer1 = new Kamer( Kamer.KamerType.GOEDKOOP , 1, 300);
            Kamer kamer2 = new Kamer( Kamer.KamerType.NORMAAL , 2, 600);
            Kamer kamer3 = new Kamer( Kamer.KamerType.DEFTIG , 4, 1000);


            Klant klant1 = new Klant("Enrico", "Emanuels", "8207594", "enricoemanels14@gmail.com", 1000.50);

            BeschikbareKamer beschikbareKamer1 = new BeschikbareKamer(true, kamer1);

            
            BetaalmethodeCrypto betaalmethodeCrypto1 = new BetaalmethodeCrypto("bitcoinadress", "BITCOIN");


            Date vervalDatumCreditCard = Date.valueOf("2029-05-01"); // formaat jaar maand dag als je info wilt opslaan over ze
            BetaalmethodeCreditcard betaalmethodeCreditcard1 = new BetaalmethodeCreditcard("Enrico Emanuels Milton", "64343423", vervalDatumCreditCard, "534");


            BetaalmethodeContant betaalmethodeContant1 = new BetaalmethodeContant("USD");


            Date verkoopDag = Date.valueOf("2025-04-11"); // formaat jaar maand dag als je info wilt opslaan over ze
            Betaalmethode betaalmethode = new Betaalmethode(Betaalmethode.MethodeType.CRYPTO, verkoopDag , klant1, betaalmethodeCrypto1);
            // extra foreign key om een subtype van betaalmethode erin te gooien

            Date startDatum = Date.valueOf("2025-04-011"); // formaat jaar maand dag als je info wilt opslaan over ze
            Date einddatum = Date.valueOf("2029-05-011"); // formaat jaar maand dag als je info wilt opslaan over ze
            KamersBoeken kamersBoeken1 = new KamersBoeken(startDatum, einddatum, 300.00, true, klant1, beschikbareKamer1, betaalmethode);
            // je kan alleen een kamer boeken als het beschikbaar is anders heeft het geen nut
            // je kan geen kamer aangeven die bestaat je weet niet of het al bezet is daarom zet
            // of heb ik een aparte kamer gemaakt voor beschikbare kamers hier gaan alleen kamers die beschikbaar zijn
            // inplaats van foreign key kamer ga ik foreign key beschibare kamer zetten
            // en een nieuwe foreign key maken voor betaalmethode





        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
