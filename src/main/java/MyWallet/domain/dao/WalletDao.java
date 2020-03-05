package MyWallet.domain.dao;

import MyWallet.domain.model.Wallet;

import java.util.List;

public interface WalletDao {
    List<Wallet> getListWallets();
}
