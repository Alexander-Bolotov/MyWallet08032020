package MyWallet.domain.dao;


import MyWallet.domain.model.TypeOfTransaction;
import MyWallet.domain.repository.TypeOfTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class TypeOTransactionDaoImpl implements TypeOTransactionDao {

    @Autowired
    private TypeOfTransactionRepository typeOfTransactionRepository;

    @Override
    public List<TypeOfTransaction> getAllTypes() {
        return typeOfTransactionRepository.findAll();
    }

    public TypeOfTransaction getTypeOfTransactionById(Long id) {
        return typeOfTransactionRepository.getOne(id);
    }

    @Override
    public TypeOfTransaction getTypeOfTransactionByName(String name) {
        return typeOfTransactionRepository.getFirstByTypeOfTransaction(name);
    }
}
