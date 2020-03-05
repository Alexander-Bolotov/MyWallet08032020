package MyWallet.domain.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transactions")
public class Transaction {

    @JsonView(Transaction.class)
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonView(Transaction.class)
    @Column(name = "date")
    private Date date;

    @JsonView(Transaction.class)
    @ManyToOne
    @JoinColumn(name = "typeOfTransaction_id",
            foreignKey = @ForeignKey(name = "typeOfTransaction_ID_FK"))
    private TypeOfTransaction typeOfTransaction;

    @JsonView(Transaction.class)
    @Column(name = "sumOfTransaction")
    private int sumOfTransaction;

    @JsonView(Transaction.class)
    @ManyToOne
    @JoinColumn(name = "income_wallet_id",
            foreignKey = @ForeignKey(name = "INCOME_WALLET_ID_FK")
    )
    private Wallet income_Wallet;

    @JsonView(Transaction.class)
    @ManyToOne
    @JoinColumn(name = "out_wallet_id",
            foreignKey = @ForeignKey(name = "OUT_WALLET_ID_FK")
    )
    private Wallet out_Wallet;

    @JsonView(Transaction.class)
    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "USER_ID_FK")
    )
    private User user;

    @JsonView(Transaction.class)
    @Column(name = "comment")
    private String comment;

    @JsonView(Transaction.class)
    @ManyToOne
    @JoinColumn(name = "category_id",
            foreignKey = @ForeignKey(name = "CATEGORY_ID_FK")
    )
    private Category category;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSumOfTransaction() {
        return sumOfTransaction;
    }

    public void setSumOfTransaction(int sumOfTransaction) {
        this.sumOfTransaction = sumOfTransaction;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", typeOfTransactions=").append(typeOfTransaction);
        sb.append(", sumOfTransaction=").append(sumOfTransaction);
        sb.append('}');
        return sb.toString();
    }
}