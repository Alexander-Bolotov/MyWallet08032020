package MyWallet.domain.repository;

import MyWallet.domain.model.Category;
import MyWallet.domain.model.TypeOfTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TypeOfTransactionRepository extends JpaRepository<TypeOfTransaction, Long> {
    @Override
    List<TypeOfTransaction> findAll();



}
