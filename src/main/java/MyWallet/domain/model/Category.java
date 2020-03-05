package MyWallet.domain.model;


import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @JsonView({Transaction.class, Category.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView({Transaction.class, Category.class})
    @Column(name = "category")
    private String category;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<TypeOfTransaction> transactions;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<TypeOfTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TypeOfTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
