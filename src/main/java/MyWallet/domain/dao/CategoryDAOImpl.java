package MyWallet.domain.dao;

import MyWallet.domain.model.Category;
import MyWallet.domain.model.TypeOfTransaction;
import MyWallet.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDao {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoriesByType(TypeOfTransaction type) {
        List<Category> categoryList = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();

        for (Category category: categoryList
             ) {
            if(category.getTypeOfTransaction().equals(type)){
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public Category getFirstByCategory(String categoryName) {
        return categoryRepository.getFirstByCategory(categoryName);
    }
}