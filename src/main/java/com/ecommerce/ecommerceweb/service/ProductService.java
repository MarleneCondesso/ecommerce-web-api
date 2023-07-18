package com.ecommerce.ecommerceweb.service;

import com.ecommerce.ecommerceweb.lib.dto.ProductDTO;
import com.ecommerce.ecommerceweb.lib.mapping.ProductDTOToProductMapper;
import com.ecommerce.ecommerceweb.lib.mapping.ProductToProductDTOMapper;
import com.ecommerce.ecommerceweb.model.Category;
import com.ecommerce.ecommerceweb.model.Product;
import com.ecommerce.ecommerceweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    ProductDTOToProductMapper productMapper = new ProductDTOToProductMapper();
    ProductToProductDTOMapper productDTOMapper = new ProductToProductDTOMapper();


    public List<ProductDTO> listProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(Product product : products){
           productDTOS.add(productDTOMapper.Map(product, product.getCategory()));
        }

        return productDTOS;
    }

    public List<Product> listAllWithCategoryDetails() {

        return productRepository.findAll();

    }
    public void createProduct(ProductDTO productDTO, Category category){

        Product product = new Product();
        product = productMapper.Map(product, productDTO, category);


        productRepository.save(product);
    }
    public void updateProduct(int id, ProductDTO updateProductDTO, Category category){

        Product product = productRepository.getById(id);

        product = productMapper.Map(product, updateProductDTO, category);

        productRepository.save(product);
    }
    public boolean readProductById(Integer id){
        return Objects.nonNull(findById(id));
    }

    public Product findById(Integer id){
       return productRepository.findById(id).get();
    }

}
