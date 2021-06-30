package com.nashtech.hanashop.service;

import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.dto.UserDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    ProductDTO createProduct(ProductDTO dto);
    String deleteProduct(String productID);
    ProductDTO updateProduct(ProductDTO dto);

}
