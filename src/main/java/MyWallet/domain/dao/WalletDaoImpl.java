package MyWallet.domain.dao;

import MyWallet.domain.model.Wallet;
import MyWallet.domain.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WalletDaoImpl implements WalletDao {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> getListWallets() {
        return walletRepository.findAll();
    }
}
