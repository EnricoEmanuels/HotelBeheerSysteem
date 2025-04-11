package hotel.systeem.entities;

import jakarta.persistence.*;
import hotel.systeem.entities.Betaalmethode;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "betaalmethode_creditcard" , schema = "hotelbeheersysteem")

public class BetaalmethodeCreditcard extends Betaalmethode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "volledigenaam", nullable = false, length = 255)
    private String volledigeNaam;

    @Column(name = "kaartnummer", nullable = false, length = 255)
    private String kaartnummer;

    @Column(name = "vervaldatum", nullable = false)
    private Date vervaldatum;

    @Column(name = "cvv", nullable = false, length = 255)
    private String cvv;

    public BetaalmethodeCreditcard() {}

    public BetaalmethodeCreditcard(Integer id, String volledigeNaam, String kaartnummer, Date vervaldatum, String cvv) {
        this.id = id;
        this.volledigeNaam = volledigeNaam;
        this.kaartnummer = kaartnummer;
        this.vervaldatum = vervaldatum;
        this.cvv = cvv;
    }
    public BetaalmethodeCreditcard(String volledigeNaam, String kaartnummer, Date vervaldatum, String cvv) {
        this.volledigeNaam = volledigeNaam;
        this.kaartnummer = kaartnummer;
        this.vervaldatum = vervaldatum;
        this.cvv = cvv;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getVolledigeNaam() {
        return volledigeNaam;
    }

    public void setVolledigeNaam(String volledigeNaam) {
        this.volledigeNaam = volledigeNaam;
    }

    public String getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(String kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getVervaldatum() {
        return vervaldatum;
    }

    public void setVervaldatum(Date vervaldatum) {
        this.vervaldatum = vervaldatum;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "BetaalmethodeCreditcard{" +
                "id=" + id +
                ", volledigeNaam='" + volledigeNaam + '\'' +
                ", kaartnummer='" + kaartnummer + '\'' +
                ", vervaldatum=" + vervaldatum +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}

