package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.dto.ProductDTO;


import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    ProductDTO createProduct(ProductDTO dto);
    String deleteProduct(String productID);
    ProductDTO updateProduct(ProductDTO dto);
    List<ProductDTO> findByName(String productName);
    List<ProductDTO> findByCategory(String categoryID);
    ProductDTO findByProductID(String productID);
    List<ProductDTO> findByNameAndCategory(String name,String category);
}
