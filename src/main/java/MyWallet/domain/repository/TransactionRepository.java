package MyWallet.domain.repository;

import MyWallet.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findAllByDate(Date date);
}
