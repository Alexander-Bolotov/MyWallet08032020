package MyWallet.domain.model;


import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;
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

    @JsonView({Transaction.class,  Category.class})
    @ManyToOne
    @JoinColumn(name = "typeOfTransaction_id",
            foreignKey = @ForeignKey(name = "TypeOfTransaction_ID_FK"))
    private TypeOfTransaction typeOfTransaction;



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

    public TypeOfTransaction getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(TypeOfTransaction typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return Objects.equals(id, category1.id) &&
                Objects.equals(category, category1.category) &&
                Objects.equals(typeOfTransaction, category1.typeOfTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, typeOfTransaction);
    }
}
