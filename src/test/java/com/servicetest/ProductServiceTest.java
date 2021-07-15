package com.servicetest;



import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.CategoryEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.repository.ProductRepository;
import com.nashtech.hanashop.service.Impl.ProductServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepo; //= Mockito.mock(ProductRepository.class);
    @InjectMocks
    private ProductServiceImpl productService;

    private List<ProductEntity> listProduct = new ArrayList<>();
    ProductEntity entity = new ProductEntity();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        entity.setCategory(new CategoryEntity());
        listProduct.add(entity);
    }

    @Test
    public void getAllSuccess_ReturnAllProduct(){
        Mockito.when(productRepo.findAll()).thenReturn(listProduct);
        Assertions.assertNotNull(listProduct);
        Assertions.assertEquals(1,productService.getAll().size());
    }

    @Test
    public void createProductSuccess_ReturnProductDTO(){
        Mockito.when(productRepo.save(Mockito.anyObject())).thenReturn(entity);
        Assertions.assertNotNull(productService.createProduct(new ProductDTO()));
    }

    @Test
    public void findByName_ReturnList(){
        Mockito.when(productRepo.findByProductNameContains(Mockito.anyString())).thenReturn(listProduct);
        Assertions.assertEquals(1,productService.findByName(Mockito.anyString()).size());

    }

    @Test
    public void findByCategory_ReturnList(){
        Mockito.when(productRepo.findByCategory(Mockito.anyObject())).thenReturn(listProduct);
        Assertions.assertEquals(1,productService.findByCategory(Mockito.anyString()).size());

    }
    @Test
    public void findByProductID_ReturnProduct(){
        Mockito.when(productRepo.findByProductID(Mockito.anyString())).thenReturn(entity);
        Assertions.assertNotNull(productService.findByProductID(Mockito.anyString()));
    }

    @Test
    public void deleteProduct_ReturnMessageSuccess(){
        Mockito.when(productRepo.findByProductID(Mockito.anyString())).thenReturn(entity);
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(entity);
        Assertions.assertEquals("Delete product success",productService.deleteProduct(Mockito.anyString()));
    }

    @Test
    public void deleteProduct_ReturnMessageFailed(){
        Mockito.when(productRepo.findByProductID(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals("Delete product failed, product not found",productService.deleteProduct(Mockito.anyString()));
    }

    @Test
    public void updateProductSuccess_ReturnProductDTO(){
        Mockito.when(productRepo.findByProductID(Mockito.any())).thenReturn(entity);
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(entity);
        Assertions.assertNotNull(productService.updateProduct(new ProductDTO()));
    }

    @Test
    public void updateProductFailed_ReturnNull(){
        Mockito.when(productRepo.findByProductID(Mockito.any())).thenReturn(null);
        Assertions.assertEquals(null,productService.updateProduct(new ProductDTO()));
    }



}
