package MyWallet.domain.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "typeOfTransaction")
public class TypeOfTransaction {

    @JsonView({Transaction.class, Category.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView({Transaction.class, Category.class})
    @Column(name = "typeOfTransaction")
    private String typeOfTransaction;

    public TypeOfTransaction() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeOfTransaction{");
        sb.append("id=").append(id);
        sb.append(", typeOfTransaction='").append(typeOfTransaction).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
