package hotel.systeem.entities;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "betaalmethode_contant" , schema = "hotelbeheersysteem")

public class BetaalmethodeContant extends Betaalmethode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;
    private String valuta;

    public BetaalmethodeContant() {}

    public BetaalmethodeContant(Integer id, String valuta) {
        this.id = id;
        this.valuta = valuta;
    }
    public BetaalmethodeContant(String valuta) {
        this.valuta = valuta;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    @Override
    public String toString() {
        return "BetaalmethodeContant{" +
                "id=" + id +
                ", valuta='" + valuta + '\'' +
                '}';
    }
}

