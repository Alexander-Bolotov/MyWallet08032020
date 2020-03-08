package MyWallet.domain.dao;


import MyWallet.domain.model.Transaction;

import java.util.Date;

import java.util.List;


public interface TransactionDao {
    List<Transaction> getTransactionsByPeriod(Date dateFirst, Date dateLast);
    List<Transaction> getAllTransactions();

    void addTransaction (Transaction transaction);

}
