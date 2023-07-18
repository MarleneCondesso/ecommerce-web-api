package com.ecommerce.ecommerceweb.controller;

import com.ecommerce.ecommerceweb.lib.api.ApiResponse;
import com.ecommerce.ecommerceweb.model.Category;
import com.ecommerce.ecommerceweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

/*    @GetMapping("/")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryService.listCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }*/

    @GetMapping("/list")
    public List<Category> getAll(){
        return categoryService.listCategories();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody Category category){
     /*   if(Objects.nonNull(categoryService.readCategoryByName(category.getName()))){
            return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.FOUND);
        }*/
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "category created"), HttpStatus.CREATED);
    }



    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") int id, @RequestBody Category category){
        if(!categoryService.readCategoryById(id)){
            return new ResponseEntity<>(new ApiResponse(false, "category doesn't exists"), HttpStatus.NOT_FOUND);
        }
        categoryService.updateCategory(id, category);
        return new ResponseEntity<>(new ApiResponse(true, "category updated"), HttpStatus.OK);
    }

}
