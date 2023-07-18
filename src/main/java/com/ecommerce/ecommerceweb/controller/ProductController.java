package com.ecommerce.ecommerceweb.controller;

import com.ecommerce.ecommerceweb.lib.dto.ProductDTO;
import com.ecommerce.ecommerceweb.lib.api.ApiResponse;
import com.ecommerce.ecommerceweb.model.Product;
import com.ecommerce.ecommerceweb.repository.CategoryRepository;
import com.ecommerce.ecommerceweb.service.CategoryService;
import com.ecommerce.ecommerceweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getAll(){
        List<ProductDTO> productsDTO = productService.listProducts();

        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }
    @GetMapping("/listWithCategory")
    public ResponseEntity<List<Product>> getAllWithCategory(){
        List<Product> products = productService.listAllWithCategoryDetails();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody ProductDTO productDTO){

        if(!categoryService.readCategoryById(productDTO.getCategory_id())){
            return new ResponseEntity<>(new ApiResponse(false, "Category doesn't exists"), HttpStatus.BAD_REQUEST);
        }

        productService.createProduct(productDTO, categoryRepository.findById(productDTO.getCategory_id()).get());

        return new ResponseEntity<>(new ApiResponse(true, "product created"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO){

        if(!categoryService.readCategoryById(id)) return new ResponseEntity<>(new ApiResponse(false, "Category doesn't exists"), HttpStatus.BAD_REQUEST);

        if(!productService.readProductById(id)) return new ResponseEntity<>(new ApiResponse(false, "Product doesn't exists"), HttpStatus.NOT_FOUND);

        productService.updateProduct(id, productDTO, categoryRepository.findById(id).get());

        return new ResponseEntity<>(new ApiResponse(true, "product updated"), HttpStatus.OK);
    }


}
