package MyWallet.domain.dao;

import MyWallet.domain.model.Category;
import MyWallet.domain.model.TypeOfTransaction;

import java.util.List;

public interface CategoryDao {

    List<Category> getCategoriesByType(TypeOfTransaction type);

    Category getFirstByCategory(String categoryName);
}
