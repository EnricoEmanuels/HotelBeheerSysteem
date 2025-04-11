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
@Table(name = "betaalmethode_crypto" , schema = "hotelbeheersysteem")

public class BetaalmethodeCrypto extends Betaalmethode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "wallet_adres", nullable = false, length = 255)
    private String walletAdres;

    @Column(name = "muntsoort", nullable = false, length = 255)
    private String muntsoort;

    public BetaalmethodeCrypto() {}

    public BetaalmethodeCrypto(Integer id, String walletAdres, String muntsoort) {
        this.id = id;
        this.walletAdres = walletAdres;
        this.muntsoort = muntsoort;
    }

    public BetaalmethodeCrypto(String walletAdres, String muntsoort) {
        this.walletAdres = walletAdres;
        this.muntsoort = muntsoort;
    }


    public String getWalletAdres() {
        return walletAdres;
    }

    public void setWalletAdres(String walletAdres) {
        this.walletAdres = walletAdres;
    }

    public String getMuntsoort() {
        return muntsoort;
    }

    public void setMuntsoort(String muntsoort) {
        this.muntsoort = muntsoort;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BetaalmethodeCrypto{" +
                "id=" + id +
                ", walletAdres='" + walletAdres + '\'' +
                ", muntsoort='" + muntsoort + '\'' +
                '}';
    }
}
