package MyWallet.domain.dao;

import MyWallet.domain.model.Transaction;
import MyWallet.domain.model.TypeOfTransaction;
import MyWallet.domain.repository.RoleRepository;
import MyWallet.domain.repository.TransactionRepository;
import MyWallet.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transaction> getTransactionsByPeriod(Date dateFirst, Date dateLast) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
//        return entityManager.createQuery("SELECT a FROM Transaction a", Transaction.class).getResultList();
        return transactionRepository.findAll();
    }


}
