package com.ecommerce.ecommerceweb.service;


import com.ecommerce.ecommerceweb.model.Category;
import com.ecommerce.ecommerceweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService  {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(Category category){

        categoryRepository.save(category);
    }

    public void updateCategory(int id, Category updateCategory){
        Category category = categoryRepository.getById(id);
        category.setDescription(updateCategory.getDescription());
        category.setName(updateCategory.getName());
        category.setImage(updateCategory.getImage());
        categoryRepository.save(category);
    }
    public boolean readCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        return optionalCategory.isPresent();


    }
    /*   public Object readCategoryByName(String name){
           return categoryRepository.findBy(name);
       }*/

}
