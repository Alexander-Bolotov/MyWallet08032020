package MyWallet.domain.dao;

import MyWallet.domain.model.TypeOfTransaction;

import java.util.List;

public interface TypeOTransactionDao {

    List<TypeOfTransaction> getAllTypes();

}
