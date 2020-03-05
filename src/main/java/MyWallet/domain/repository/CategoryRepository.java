package MyWallet.domain.repository;

import MyWallet.domain.model.Category;
import MyWallet.domain.model.TypeOfTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllById(Long i);
    List<Category> findAllByType(TypeOfTransaction typeOfTransaction);
}
