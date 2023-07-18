package com.ecommerce.ecommerceweb.lib.mapping;

import com.ecommerce.ecommerceweb.lib.dto.ProductDTO;
import com.ecommerce.ecommerceweb.model.Category;
import com.ecommerce.ecommerceweb.model.Product;
import org.mapstruct.Mapper;

@Mapper
public class ProductDTOToProductMapper {

    public ProductDTOToProductMapper(){

    }

    public Product Map(Product product, ProductDTO productDTO, Category category){

        product.setId(product.getId());
        product.setDescription(productDTO.getDescription() == null ? product.getDescription() : productDTO.getDescription());
        product.setName(productDTO.getName() == null ? product.getName() : productDTO.getName());
        product.setImage(productDTO.getImage() == null ? product.getImage() : productDTO.getImage());
        product.setPrice(productDTO.getPrice() == 0 ? product.getPrice() : productDTO.getPrice());
        product.setCategory(category == null ? product.getCategory() : category);

        return product;
    }
}
