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

import hotel.systeem.services.HotelBoekenService;

import java.sql.Date;
import java.time.LocalDate;


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

            Date verkoopdag3 = Date.valueOf("2025-4-15");
            HotelBoekenService hotelBoekenService = new HotelBoekenService(klantDao, betaalmethodeDao, betaalmethodeCryptoDao);
//            hotelBoekenService.hotelBoekenMetCrypto("Damani", "Resida", "7549834", "damani@amil.com", 4444.00, Betaalmethode.MethodeType.crypto, verkoopdag3, "solana adres", "SOLONA"); // perfect

            HotelBoekenService hotelBoekenService1 = new HotelBoekenService(klantDao, betaalmethodeDao, betaalmethodeCreditcardDao);
            Date verkoopdag4 = Date.valueOf("2025-07-22");
            Date vervaldatum5 = Date.valueOf("2029-07-22");
//            hotelBoekenService1.hotelBoekenMetCreditcard("Keith", "Graanoost", "86588945", "keith@gmail.com", 6000.40, Betaalmethode.MethodeType.creditcard, verkoopdag4, "Keith Graanoost", "745834884", vervaldatum5, "456"); // perfect

            HotelBoekenService hotelBoekenService2 = new HotelBoekenService(klantDao, betaalmethodeDao, betaalmethodeContantDao);
            Date verkoopdag5 = Date.valueOf("2025-09-11");
//            hotelBoekenService2.hotelBoekenMetContant("ramlal", "shiva", "7485853", "ramlal@gmail.com", 2323.90, Betaalmethode.MethodeType.contant, verkoopdag5, "USD" ); // nog afmaken

            HotelBoekenService hotelBoekenService3 = new HotelBoekenService(klantDao, betaalmethodeDao, kamerDao, beschikbareKamerDao, kamersBoekenDao);
            Date verkoopdag6 = Date.valueOf("2025-04-17");
            Date startdatum4 = Date.valueOf("2025-04-04");
            Date einddatum4 = Date.valueOf("2025-04-04");

            // het werkt goed ik dacht juist dat het een error zou geven
//            hotelBoekenService3.nieuweKamerCreerenEnDirekBoeken("Tasha", "hoogvliets", "785694", "tasha@gmail", 1234.23, Betaalmethode.MethodeType.creditcard, verkoopdag6, Kamer.KamerType.goedkoop, 1, 300.00, "Beschikbaar", startdatum4, einddatum4);

            // bij kamers boeken denk ik dat we als laatse dgeen kamer maar bescikbare kamers moeten plaatsen
            // want bij beschikbare kamers ga ik al kamers toeveoege die beschikbaar zijn
            // extra forein key zetten voor betaalmethode

            Kamer kamer1 = new Kamer( Kamer.KamerType.goedkoop , 1, 300);
            Kamer kamer2 = new Kamer( Kamer.KamerType.normaal , 2, 600);
            Kamer kamer3 = new Kamer( Kamer.KamerType.deftig , 4, 1000);

            Kamer bestaandekamer2 = kamerDao.findById(2);
            BeschikbareKamer beschikbareKamer2 = new BeschikbareKamer(BeschikbareKamer.BeschikbareKamerAlternatief.beschikbaar, bestaandekamer2);

            Kamer bestaandekamer3 = kamerDao.findById(3);
            BeschikbareKamer beschikbareKamer3 = new BeschikbareKamer(BeschikbareKamer.BeschikbareKamerAlternatief.beschikbaar, bestaandekamer3);



            Klant klant1 = new Klant("Enrico", "Emanuels", "8207594", "enricoemanels14@gmail.com", 1000.50);
            Klant klant2 = new Klant("Justin", "Emanuels", "8537843", "justin@gmail.com", 567.87);


            Kamer bestaandekamer1 = kamerDao.findById(1);
            BeschikbareKamer beschikbareKamer1 = new BeschikbareKamer(BeschikbareKamer.BeschikbareKamerAlternatief.beschikbaar, bestaandekamer1); // dit is die correcte manier

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


//            LocalDate dag = LocalDate.valueOf();

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
            KamersBoeken kamersBoeken1 = new KamersBoeken(startDatum, einddatum, klantdiebetaaldmetcrypto, beschikbarekamertehuur, betaalmethodeviacrypto);
            // je kan alleen een kamer boeken als het beschikbaar is anders heeft het geen nut
            // je kan geen kamer aangeven die bestaat je weet niet of het al bezet is daarom zet
            // of heb ik een aparte kamer gemaakt voor beschikbare kamers hier gaan alleen kamers die beschikbaar zijn
            // inplaats van foreign key kamer ga ik foreign key beschibare kamer zetten
            // en een nieuwe foreign key maken voor betaalmethode
            //
            //

            //            betaalmethodeContantDao.save(betaalmethodeContant1); // het zegt het is succesvol gegaan maar het komt niet voor in de datase bij alle subsclassen



            // Alle save methodes werken perfect


//            klantDao.save(klant1); // succes
//            klantDao.save(klant2); // succes

//            kamerDao.save(kamer1); // succes
//            kamerDao.save(kamer2); // succes
//            kamerDao.save(kamer3); // succes


//              beschikbareKamerDao.save(beschikbareKamer1); // succes
//            beschikbareKamerDao.save(beschikbareKamer2); // werkt
//            beschikbareKamerDao.save(beschikbareKamer3); // werkt



//            System.out.println(klantDao.findById(2)); // werkt

//            betaalmethodeDao.save(betaalmethode); // werkt
//            betaalmethodeDao.save(betaalmethode2); // werkt
//            betaalmethodeDao.save(betaalmethode3); // werkt


//            System.out.println(kamerDao.findById(4)); // het werkt
//            kamerDao.deleteById(4);


//            betaalmethodeCryptoDao.save(betaalmethodeCrypto1); // werkt
//
//            betaalmethodeCreditcardDao.save(betaalmethodeCreditcard1); // werkt perfect
//
//            betaalmethodeContantDao.save(betaalmethodeContant1); // werkt perfect

//            System.out.println(beschikbareKamerDao.findById(1)); // werkt

//            kamersBoekenDao.save(kamersBoeken1); // eindelijk werkt

            // alle findbyID werken

//            System.out.println(klantDao.findById(2)); // werkt
//            System.out.println(kamersBoekenDao.findById(1)); // werkt ik moest die tostring aanpassen bij die foreign keys
//            System.out.println(kamerDao.findById(3)); // werkt
//            System.out.println(betaalmethodeDao.findById(3)); // perfect
//            System.out.println(betaalmethodeCryptoDao.findById(1)); // werkt
//            System.out.println(betaalmethodeCreditcardDao.findById(3)); //werkt
//            System.out.println(betaalmethodeContantDao.findById(2)); // werkt
//            System.out.println(beschikbareKamerDao.findById(1)); // perfect

            // alle findAll werken
//            System.out.println(klantDao.findAll()); // werkt
//            System.out.println(kamersBoekenDao.findAll()); // werkt
//            System.out.println(kamerDao.findAll()); // werkt
//            System.out.println(betaalmethodeDao.findAll()); // werrkt
//            System.out.println(betaalmethodeCryptoDao.findAll()); //werkt
//            System.out.println(betaalmethodeCreditcardDao.findAll()); // werkt
//            System.out.println(betaalmethodeContantDao.findAll()); //werkt
//            System.out.println(beschikbareKamerDao.findAll()); // werkt

            // alle updates werken behalve betaalmethode

            Klant klantupdaten = new Klant(2, "Hanna", "Christa", "5473498", "hanna@gmail.com", 2344.99);
//            klantDao.update(klantupdaten); // succes


            Date startDatumupdate = Date.valueOf("2025-08-21"); // formaat jaar maand dag als je info wilt opslaan over ze
            Date einddatumupdate = Date.valueOf("2025-09-21"); // formaat jaar maand d

            Klant klantdiebetaaldmetcreditcard = klantDao.findById(2); // id zoeken van de klant die met crypto betaald
            // id zoeken van een beschikbare kamer
            BeschikbareKamer beschikbarekamertehuur2 = beschikbareKamerDao.findById(1);
            // id zoeken van de betaalmethode die met crypto heeft betaald
            Betaalmethode betaalmethodeviacreditcard = betaalmethodeDao.findById(3);

            KamersBoeken kamersboekenupdaten = new KamersBoeken(1, startDatumupdate, einddatumupdate, klantdiebetaaldmetcreditcard, beschikbarekamertehuur2 , betaalmethodeviacreditcard);
//            kamersBoekenDao.update(kamersboekenupdaten); // werkt

            Kamer kamerupdaten = new Kamer(1, Kamer.KamerType.normaal, 2, 600.00);
//            kamerDao.update(kamerupdaten); // werkt

            Date datumupdaten1 = Date.valueOf("2025-04-13"); // 13 april 2025
            Klant klantveranderen = klantDao.findById(2);
            Betaalmethode betaalmethodeupdaten = new Betaalmethode(3, Betaalmethode.MethodeType.crypto, datumupdaten1, klantveranderen );
//            betaalmethodeDao.update(betaalmethodeupdaten); // Cannot delete or update a parent row: a foreign key constraint fails (`hotelbeheersysteem`.`betaalmethode`, CONSTRAINT `betaalmethode_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`id`))

            BetaalmethodeCrypto betaalmethodeCryptoupdate = new BetaalmethodeCrypto(1, "etherium adress", "etherium");
//            betaalmethodeCryptoDao.update(betaalmethodeCryptoupdate); // werkt

            Date updatevervaldatum = Date.valueOf("2030-02-14");
            BetaalmethodeCreditcard betaalmethodeCreditcardUpdaten = new BetaalmethodeCreditcard(3, "Milton Deborah", "679384859", updatevervaldatum, "345");
//            betaalmethodeCreditcardDao.update(betaalmethodeCreditcardUpdaten); // wekt

            BetaalmethodeContant updatebetaalmethodecontant = new BetaalmethodeContant(2, "EURO");
//            betaalmethodeContantDao.update(updatebetaalmethodecontant); // werkt

            Kamer kamerupdaten2 = kamerDao.findById(3);
            BeschikbareKamer updatebeschikbarekamer = new BeschikbareKamer(1, BeschikbareKamer.BeschikbareKamerAlternatief.beschikbaar, kamerupdaten2 );
//            beschikbareKamerDao.update(updatebeschikbarekamer); // werkt

            // alle deletebyid
//            klantDao.deleteById(2); // Cannot delete or update a parent row: a foreign key constraint fails (`hotelbeheersysteem`.`betaalmethode`, CONSTRAINT `betaalmethode_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`id`))

//            kamersBoekenDao.deleteById(1); // Cannot delete or update a parent row: a foreign key constraint fails (`hotelbeheersysteem`.`betaalmethode`, CONSTRAINT `betaalmethode_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`id`))

//            kamerDao.deleteById(1); // werkt

//            betaalmethodeDao.deleteById(2); // Cannot delete or update a parent row: a foreign key constraint fails (`hotelbeheersysteem`.`betaalmethodecontant`, CONSTRAINT `betaalmethodecontant_ibfk_1` FOREIGN KEY (`id`) REFERENCES `betaalmethode` (`id`))

//            betaalmethodeCryptoDao.deleteById(1); // werkt

//            betaalmethodeCreditcardDao.deleteById(3); // werkt

//            betaalmethodeContantDao.deleteById(2); // werkt

//            beschikbareKamerDao.deleteById(1); // Cannot delete or update a parent row: a foreign key constraint fails (`hotelbeheersysteem`.`kamersboeken`, CONSTRAINT `kamersboeken_ibfk_2` FOREIGN KEY (`beschikbarekamer_id`) REFERENCES `beschikbarekamers` (`id`))

//            klantDao.opwaarderen(1, 3333.90); // werkt perfect


            // proberen voor die methode kamersboeken
            Klant nieuweklant = new Klant("Donavan", "Frangie", "8584843", "donavan@gmail.com", 2000);
//            klantDao.save(nieuweklant);


            Betaalmethode betaalmethodeZettenInKlant2 = betaalmethodeDao.findById(3);
            Klant nieuweklant2 = new Klant("Remuel", "Tholel", "7856594", "remuel@gmail.com", 1500, betaalmethodeZettenInKlant2);
//            klantDao.save(nieuweklant2); // dus je kan direk een nieuwe klant maken en direk een niewwe betaalmethode plaatsen
            // dat is handig man  je kan ook een klant apart maken zonder dat hij een betaalmethode zet
            // en pas later als hij zin heeft kan hij een betaalmethode zetten maar dan moet ik a; zij gegevens stieken updaten met zijn
            // eigen gegevens anders kan het voor problemen zorgen bij die update



            Date nieuweDatum = Date.valueOf("2025-04-13");
            Klant idVanKlant = klantDao.findById(3);
            Betaalmethode nieuwebetaalmethode = new Betaalmethode(Betaalmethode.MethodeType.crypto, nieuweDatum, idVanKlant);

//            betaalmethodeDao.save(nieuwebetaalmethode); // werkt

            Betaalmethode betaalmethodeZettenInKlant = betaalmethodeDao.findById(4);
            Klant klant3UpdatenMetEenBetaalmethode = new Klant(3, "Donavan", "Frangie", "8584843", "donavan@gmail.com", 2000, betaalmethodeZettenInKlant);
//            klantDao.update(klant3UpdatenMetEenBetaalmethode); // eindelijk is het gegaan

            BeschikbareKamer beschikbareKamer = beschikbareKamerDao.findById(3);
//            klantDao.kamerboeken(3, beschikbareKamer); // werkt

//            System.out.println(beschikbareKamerDao.alleBeschikbareKamers()); // werkt

//            System.out.println(betaalmethodeDao.alleBetaalmethodesVanKlant(2)); // werkt


//            klantDao.klantUpdatenMetBetaalmethode(1, 2); // het werkt

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

