package hotel.systeem.entities;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import hotel.systeem.entities.Klant;
import hotel.systeem.entities.Kamer;

@Entity
public class KamersBoeken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "startdatum", nullable = false)
    private Date startdatum;

    @Column(name = "einddatum", nullable = false)
    private Date einddatum;

    @Column(name = "totaalbedrag", nullable = false)
    private double totaalbedrag;

    @Column(name = "betaald", nullable = false)
    private boolean betaald;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "klant_id", referencedColumnName = "id")
//    @Column(name = "klant_id", nullable = false)
    private Klant klant;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "klant_id", referencedColumnName = "id")
//    @Column(name = "kamer_id", nullable = false)
    private Kamer kamer;

    public KamersBoeken() {}


    public KamersBoeken(int id, Date startdatum, Date einddatum, double totaalbedrag, boolean betaald, Klant klant, Kamer kamer) {
        this.id = id;
        this.startdatum = startdatum;
        this.einddatum = einddatum;
        this.totaalbedrag = totaalbedrag;
        this.betaald = betaald;
        this.klant = klant;
        this.kamer = kamer;
    }

    public KamersBoeken(Date startdatum, Date einddatum, double totaalbedrag, boolean betaald, Klant klant, Kamer kamer) {
        this.startdatum = startdatum;
        this.einddatum = einddatum;
        this.totaalbedrag = totaalbedrag;
        this.betaald = betaald;
        this.klant = klant;
        this.kamer = kamer;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(Date startdatum) {
        this.startdatum = startdatum;
    }

    public Date getEinddatum() {
        return einddatum;
    }

    public void setEinddatum(Date einddatum) {
        this.einddatum = einddatum;
    }

    public double getTotaalbedrag() {
        return totaalbedrag;
    }

    public void setTotaalbedrag(double totaalbedrag) {
        this.totaalbedrag = totaalbedrag;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Kamer getKamer() {
        return kamer;
    }

    public void setKamer(Kamer kamer) {
        this.kamer = kamer;
    }



    @Override
    public String toString() {
        return "KamersBoeken{" +
                "id=" + id +
                ", startdatum=" + startdatum +
                ", einddatum=" + einddatum +
                ", totaalbedrag=" + totaalbedrag +
                ", betaald=" + betaald +
                ", klant=" + klant +
                ", kamer=" + kamer +
                '}';
    }
}