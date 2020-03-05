package MyWallet.domain.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Wallets")
public class Wallet implements Serializable {

    @JsonView({Transaction.class, Wallet.class})
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonView({Transaction.class, Wallet.class})
    @Column(name = "wallet", unique = true)
    private String wallet;

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", wallet='" + wallet + '\'' +
                '}';
    }
}
