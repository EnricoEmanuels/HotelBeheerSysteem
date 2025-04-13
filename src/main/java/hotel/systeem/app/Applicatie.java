package hotel.systeem.app;
import hotel.systeem.dao.*;
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

            KlantDao klantDao = new KlantDao(entityManager);
            KamersBoekenDao kamersBoekenDao = new KamersBoekenDao(entityManager);
            KamerDao kamerDao = new KamerDao(entityManager);
            BetaalmethodeDao betaalmethodeDao = new BetaalmethodeDao(entityManager);
            BetaalmethodeCryptoDao betaalmethodeCryptoDao = new BetaalmethodeCryptoDao(entityManager);
            BetaalmethodeContantDao betaalmethodeContantDao = new BetaalmethodeContantDao(entityManager);
            BeschikbareKamerDao beschikbareKamerDao = new BeschikbareKamerDao(entityManager);
            BetaalmethodeCreditcardDao betaalmethodeCreditcardDao = new BetaalmethodeCreditcardDao(entityManager);

            // bij kamers boeken denk ik dat we als laatse dgeen kamer maar bescikbare kamers moeten plaatsen
            // want bij beschikbare kamers ga ik al kamers toeveoege die beschikbaar zijn
            // extra forein key zetten voor betaalmethode

            Kamer kamer1 = new Kamer( Kamer.KamerType.goedkoop , 1, 300);
            Kamer kamer2 = new Kamer( Kamer.KamerType.normaal , 2, 600);
            Kamer kamer3 = new Kamer( Kamer.KamerType.deftig , 4, 1000);


            Klant klant1 = new Klant("Enrico", "Emanuels", "8207594", "enricoemanels14@gmail.com", 1000.50);
            Klant klant2 = new Klant("Justin", "Emanuels", "8537843", "justin@gmail.com", 567.87);


            Kamer bestaandekamer1 = kamerDao.findById(1);
            BeschikbareKamer beschikbareKamer1 = new BeschikbareKamer("Beschikbaar", bestaandekamer1); // dit is die correcte manier

            // bij betaalmethode heeft id 1 met crypto betaald dus dan moet je handmatig
            // die 1 invullen in ide betaalmethodecrypto
            Betaalmethode bestaandebetaalmethode = betaalmethodeDao.findById(1);
            BetaalmethodeCrypto betaalmethodeCrypto1 = new BetaalmethodeCrypto(bestaandebetaalmethode,"bitcoinadress", "BITCOIN" );

            // bij betaalmethode heeft id 3 met creditcaard betaaald dus dan
            // kan je dit handmatig plaasen erin

            Betaalmethode bestaandecreditkaartgebruiker = betaalmethodeDao.findById(3);
            Date vervalDatumCreditCard = Date.valueOf("2029-05-01"); // formaat jaar maand dag als je info wilt opslaan over ze
            BetaalmethodeCreditcard betaalmethodeCreditcard1 = new BetaalmethodeCreditcard(bestaandecreditkaartgebruiker,"Enrico Emanuels Milton", "64343423", vervalDatumCreditCard, "534");


            // bij methode heeft id 2 met contant betaald dus dan moet je deze waarde
            // of id handmatig plaatsen in betaalmethodeContant

            Betaalmethode bestaandecontantgebruiker = betaalmethodeDao.findById(2);
            BetaalmethodeContant betaalmethodeContant1 = new BetaalmethodeContant(bestaandecontantgebruiker,"USD");



            Klant bestaandeklant = klantDao.findById(2); // werkt
            Date verkoopDag = Date.valueOf("2025-04-11"); // formaat jaar maand dag als je info wilt opslaan over ze
            Betaalmethode betaalmethode = new Betaalmethode(Betaalmethode.MethodeType.crypto, verkoopDag , bestaandeklant);
            // extra foreign key om een subtype van betaalmethode erin te gooien

            Klant bestaandeklant2 = klantDao.findById(1); // werkt
            Date verkoopDag2 = Date.valueOf("2025-04-11"); // formaat jaar maand dag als je info wilt opslaan over ze
            Betaalmethode betaalmethode2 = new Betaalmethode(Betaalmethode.MethodeType.contant, verkoopDag , bestaandeklant2);

            Klant bestaandeklant3 = klantDao.findById(2); // werkt
            Date verkoopDag3 = Date.valueOf("2025-04-11"); // formaat jaar maand dag als je info wilt opslaan over ze
            Betaalmethode betaalmethode3 = new Betaalmethode(Betaalmethode.MethodeType.creditcard, verkoopDag , bestaandeklant3);



            Date startDatum = Date.valueOf("2025-04-11"); // formaat jaar maand dag als je info wilt opslaan over ze
            Date einddatum = Date.valueOf("2025-05-11"); // formaat jaar maand dag als je info wilt opslaan over ze


            Klant klantdiebetaaldmetcrypto = klantDao.findById(1); // id zoeken van de klant die met crypto betaald
            // id zoeken van een beschikbare kamer
            BeschikbareKamer beschikbarekamertehuur = beschikbareKamerDao.findById(1);
            // id zoeken van de betaalmethode die met crypto heeft betaald
            Betaalmethode betaalmethodeviacrypto = betaalmethodeDao.findById(1);

            // op deze manier ga je geen nieuwe informatie dupliceren in die andere tabellen je zoek
            // gewoon hun ID en je plaats het in de kamersboeken
            KamersBoeken kamersBoeken1 = new KamersBoeken(startDatum, einddatum, 300.00, "crypto", klantdiebetaaldmetcrypto, beschikbarekamertehuur, betaalmethodeviacrypto);
            // je kan alleen een kamer boeken als het beschikbaar is anders heeft het geen nut
            // je kan geen kamer aangeven die bestaat je weet niet of het al bezet is daarom zet
            // of heb ik een aparte kamer gemaakt voor beschikbare kamers hier gaan alleen kamers die beschikbaar zijn
            // inplaats van foreign key kamer ga ik foreign key beschibare kamer zetten
            // en een nieuwe foreign key maken voor betaalmethode
            //
            //
            //
//            klantDao.save(klant1); // succes
//            klantDao.save(klant2); // succes

//            kamerDao.save(kamer1); // succes
//            kamerDao.save(kamer2); // succes
//            kamerDao.save(kamer3); // succes


//              beschikbareKamerDao.save(beschikbareKamer1); // succes



//            System.out.println(klantDao.findById(2)); // werkt

//            betaalmethodeDao.save(betaalmethode); // werkt
//            betaalmethodeDao.save(betaalmethode2); // werkt
//            betaalmethodeDao.save(betaalmethode3); // werkt


//            System.out.println(kamerDao.findById(4)); // het werkt
//            kamerDao.deleteById(4);


//            betaalmethodeCryptoDao.save(betaalmethodeCrypto1); // werkt

//            betaalmethodeCreditcardDao.save(betaalmethodeCreditcard1); // werkt perfect

//            betaalmethodeContantDao.save(betaalmethodeContant1); // werkt perfect
            System.out.println(beschikbareKamerDao.findById(1)); // werkt

            kamersBoekenDao.save(kamersBoeken1); // eindelijk werkt







           // betaalmethodeCreditcardDao.save(betaalmethodeCreditcard1);


//            betaalmethodeContantDao.save(betaalmethodeContant1); // het zegt het is succesvol gegaan maar het komt niet voor in de datase bij alle subsclassen

//            betaalmethodeDao.save(betaalmethode); // die andere moeten eerst opgelsgen worden

            //            klantDao.deleteById(); // nog niet geprobeert


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
