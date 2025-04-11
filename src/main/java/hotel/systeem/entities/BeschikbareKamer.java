package hotel.systeem.entities;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "beschikbarekamers" , schema = "hotelbeheersysteem")

public class BeschikbareKamer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "beschikbaar", nullable = false)
    private boolean beschikbaar;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "kamer_id", referencedColumnName = "id")
    private Kamer kamer;

    public BeschikbareKamer() {}

    public BeschikbareKamer(int id, boolean beschikbaar, Kamer kamer) {
        this.id = id;
        this.beschikbaar = beschikbaar;
        this.kamer = kamer;
    }

    public BeschikbareKamer(boolean beschikbaar, Kamer kamer) {
        this.beschikbaar = beschikbaar;
        this.kamer = kamer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    public Kamer getKamer() {
        return kamer;
    }

    public void setKamer(Kamer kamer) {
        this.kamer = kamer;
    }

    @Override
    public String toString() {
        return "BeschikbareKamer{" +
                "id=" + id +
                ", beschikbaar=" + beschikbaar +
                ", kamer=" + kamer +
                '}';
    }
}