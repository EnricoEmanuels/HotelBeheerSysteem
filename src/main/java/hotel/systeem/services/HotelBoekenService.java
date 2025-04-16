package hotel.systeem.services;

import hotel.systeem.dao.*;
import hotel.systeem.entities.*;

import java.util.Date;
import hotel.systeem.entities.Betaalmethode.MethodeType;

import hotel.systeem.entities.Kamer.KamerType;

import hotel.systeem.entities.BeschikbareKamer.BeschikbareKamerAlternatief; // we importeren die enum

public class HotelBoekenService {
    private KlantDao klantDao;
    private BetaalmethodeDao betaalmethodeDao;
    private KamerDao kamerDao;
    private KamersBoekenDao kamersBoekenDao;
    private BetaalmethodeCryptoDao betaalmethodeCryptoDao;
    private BetaalmethodeCreditcardDao betaalmethodeCreditcardDao;
    private BetaalmethodeContantDao betaalmethodeContantDao;
    private BeschikbareKamerDao beschikbareKamerDao;


    public HotelBoekenService(KlantDao klantDao, BetaalmethodeDao betaalmethodeDao, BetaalmethodeCryptoDao betaalmethodeCryptoDao) {
        this.klantDao = klantDao;
        this.betaalmethodeDao = betaalmethodeDao;
        this.betaalmethodeCryptoDao = betaalmethodeCryptoDao;
    }

    public HotelBoekenService(KlantDao klantDao, BetaalmethodeDao betaalmethodeDao, BetaalmethodeCreditcardDao betaalmethodeCreditcardDao) {
        this.klantDao = klantDao;
        this.betaalmethodeDao = betaalmethodeDao;
        this.betaalmethodeCreditcardDao = betaalmethodeCreditcardDao;
    }

    public HotelBoekenService(KlantDao klantDao, BetaalmethodeDao betaalmethodeDao, BetaalmethodeContantDao betaalmethodeContantDao) {
        this.klantDao = klantDao;
        this.betaalmethodeDao = betaalmethodeDao;
        this.betaalmethodeContantDao = betaalmethodeContantDao;
    }

    public HotelBoekenService(KlantDao klantDao, BetaalmethodeDao betaalmethodeDao, KamerDao kamerDao, BeschikbareKamerDao beschikbareKamerDao ,  KamersBoekenDao kamersBoekenDao) {
        this.klantDao = klantDao;
        this.betaalmethodeDao = betaalmethodeDao;
        this.kamerDao = kamerDao;
        this.beschikbareKamerDao = beschikbareKamerDao;
        this.kamersBoekenDao = kamersBoekenDao;
    }


    // wanner je een object maakt wordt de constructor automatisch aangeroepen
    // HotelBoekenService pp = new HotelBoekenService(klantDao, betaalmethodeDao, betaalmethodeCryptoDao);
    // pp.hotelBoekenMetCrypto()  waardes zetten hierin  // die methode accepteert waardes via die parameter
    public void hotelBoekenMetCrypto(String voornaam, String achternaam, String telefoonnummer, String email, double balans, MethodeType methodeType, Date verkoopdag, String walletAdres , String muntSoort) {

        // klant opslaan
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTelefoon(telefoonnummer);
        klant.setEmail(email);
        klant.setBalans(balans);
        klantDao.save(klant);

        // betaalmethode opslaan
        Betaalmethode betaalmethode = new Betaalmethode();
        betaalmethode.setMethode(methodeType);
        betaalmethode.setDatum(verkoopdag);
        betaalmethode.setKlant(klant);
        betaalmethodeDao.save(betaalmethode);

        BetaalmethodeCrypto betaalmethodeCrypto = new BetaalmethodeCrypto();
        // omdat ik een one to one relatie heb met betaakmethode en ik heb ook een contructor die Betaalmethode
        // accpeteerdt in plaats van een normale integer en het is geen autoincrement omdat het dezelfde
        // ID moet hebben zoals die van die betaakmethode
        betaalmethodeCrypto.setBetaalmethode(betaalmethode);
//        betaalmethodeCrypto.setId(betaalmethode.getId());
        betaalmethodeCrypto.setWalletAdres(walletAdres);
        betaalmethodeCrypto.setMuntsoort(muntSoort);
        betaalmethodeCryptoDao.save(betaalmethodeCrypto);
    }

    public void hotelBoekenMetCreditcard(String voornaam, String achternaam, String telefoonnummer, String email, double balans, MethodeType methodeType, Date verkoopdag, String volledigeNaam, String kaartNummer, Date vervaldatum, String CCV) {
        // klant opslaan
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTelefoon(telefoonnummer);
        klant.setEmail(email);
        klant.setBalans(balans);
        klantDao.save(klant);

        // betaalmethode opslaan
        Betaalmethode betaalmethode = new Betaalmethode();
        betaalmethode.setMethode(methodeType);
        betaalmethode.setDatum(verkoopdag);
        betaalmethode.setKlant(klant);
        betaalmethodeDao.save(betaalmethode);

        BetaalmethodeCreditcard betaalmethodeCreditcard = new BetaalmethodeCreditcard();
        betaalmethodeCreditcard.setBetaalmethode(betaalmethode);
        betaalmethodeCreditcard.setVolledigeNaam(volledigeNaam);
        betaalmethodeCreditcard.setKaartnummer(kaartNummer);
        betaalmethodeCreditcard.setVervaldatum(vervaldatum);
        betaalmethodeCreditcard.setCvv(CCV);
        betaalmethodeCreditcardDao.save(betaalmethodeCreditcard);
    }

    public void hotelBoekenMetContant(String voornaam, String achternaam, String telefoonnummer, String email, double balans, MethodeType methodeType, Date verkoopdag, String valuta) {
        // klant opslaan
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTelefoon(telefoonnummer);
        klant.setEmail(email);
        klant.setBalans(balans);
        klantDao.save(klant);

        // betaalmethode opslaan
        Betaalmethode betaalmethode = new Betaalmethode();
        betaalmethode.setMethode(methodeType);
        betaalmethode.setDatum(verkoopdag);
        betaalmethode.setKlant(klant);
        betaalmethodeDao.save(betaalmethode);

        BetaalmethodeContant betaalmethodeContant = new BetaalmethodeContant();
        betaalmethodeContant.setBetaalmethode(betaalmethode);
        betaalmethodeContant.setValuta(valuta);
        betaalmethodeContantDao.save(betaalmethodeContant);

    }

    public void nieuweKamerCreerenEnDirekBoeken(String voornaam, String achternaam, String telefoonnummer, String email, double balans, MethodeType methodeType, Date verkoopdag , KamerType kamerType, int aantalBedden, double prijsPerMaand, BeschikbareKamerAlternatief isdezekamerbeschikbaar, Date startDatum, Date eindDatum) {
        // klant opslaan
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTelefoon(telefoonnummer);
        klant.setEmail(email);
        klant.setBalans(balans);
        klantDao.save(klant);

        // betaalmethode opslaan
        Betaalmethode betaalmethode = new Betaalmethode();
        betaalmethode.setMethode(methodeType);
        betaalmethode.setDatum(verkoopdag);
        betaalmethode.setKlant(klant);
        betaalmethodeDao.save(betaalmethode);

        // kamer creeren
        Kamer kamer = new Kamer();
        kamer.setKamertype(kamerType);
        kamer.setAantalbedden(aantalBedden);
        kamer.setPrijsPerMaand(prijsPerMaand);
        kamerDao.save(kamer);

        // beschikbare kamer creeren
        BeschikbareKamer beschikbareKamer = new BeschikbareKamer();
        beschikbareKamer.setBeschikbareKamerAlternatief(isdezekamerbeschikbaar);
        beschikbareKamer.setKamer(kamer);

        beschikbareKamerDao.save(beschikbareKamer);

        // kamer boeken
        KamersBoeken kamersBoeken = new KamersBoeken();
        kamersBoeken.setStartdatum(startDatum);
        kamersBoeken.setEinddatum(eindDatum);
        kamersBoeken.setKlant(klant);
        kamersBoeken.setBeschikbareKamer(beschikbareKamer);
        kamersBoeken.setBetaalmethodes(betaalmethode);

        kamersBoekenDao.save(kamersBoeken);

        // beschikbare kamer stellen op niet meer beschikbaar omdat je het zal gebruiken
        beschikbareKamer.setId(beschikbareKamer.getId());
        beschikbareKamer.setBeschikbareKamerAlternatief(BeschikbareKamer.BeschikbareKamerAlternatief.nietbeschikbaar);
        beschikbareKamer.setKamer(kamer);

        // mijn hypothese is dat je een tabel die een foreign key heeft niet kan
        // verandere of updaten dus daarom probeer ik die zet
        // dsu ik wou die merge doen want update heeft die merge maar om die merge te doen heb
        // je die entity manager nodig

        // het werkt perfect
        beschikbareKamerDao.update(beschikbareKamer);
//        entityManager.merge(beschikbareKamer);
        System.out.println("succesvol geboekt");


    }










}
