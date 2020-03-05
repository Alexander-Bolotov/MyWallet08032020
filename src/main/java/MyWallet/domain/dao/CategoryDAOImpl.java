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
    public List<Category> getCategoryIN() {
        return categoryRepository.findAllById((long)1);
    }

    @Override
    public List<Category> getCategoryOUT() {
        return categoryRepository.findAllById((long)2);
    }

    @Override
    public List<Category> getCategoriesByType(TypeOfTransaction type) {
        List<Category> categoryList = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();

        for (Category category: categoryList
             ) {
            if(category.getCategory().equals(type)){
                categories.add(category);
            }
        }
        return categories;
    }
}