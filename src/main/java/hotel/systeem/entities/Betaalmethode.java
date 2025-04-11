package hotel.systeem.entities;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "betaalmethode" , schema = "hotelbeheersysteem")

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Betaalmethode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "methode", nullable = false)
    private MethodeType methode;

    @Column(name = "datum", nullable = false)
    private Date datum;

    public enum MethodeType {
        CRYPTO, CREDITCARD, CONTANT
    }

    public Betaalmethode() {

    }

    public Betaalmethode(Integer id, MethodeType methode, Date datum) {
        this.id = id;
        this.methode = methode;
        this.datum = datum;
    }

    public Betaalmethode(MethodeType methode, Date datum) {
        this.methode = methode;
        this.datum = datum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MethodeType getMethode() {
        return methode;
    }

    public void setMethode(MethodeType methode) {
        this.methode = methode;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Betaalmethode{" +
                "id=" + id +
                ", methode=" + methode +
                ", datum=" + datum +
                '}';
    }
}

