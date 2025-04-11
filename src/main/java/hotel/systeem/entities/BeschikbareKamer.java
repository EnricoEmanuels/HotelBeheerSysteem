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

    public BeschikbareKamer() {}

    public BeschikbareKamer(int id, boolean beschikbaar) {
        this.id = id;
        this.beschikbaar = beschikbaar;
    }

    public BeschikbareKamer(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
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

    @Override
    public String toString() {
        return "BeschikbareKamer{" +
                "id=" + id +
                ", beschikbaar=" + beschikbaar +
                '}';
    }
}