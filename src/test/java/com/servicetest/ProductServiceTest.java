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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAllSuccess_ReturnAllProduct(){
        List<ProductEntity> listProduct = new ArrayList<>();
        ProductEntity pro1 = new ProductEntity();
        ProductEntity pro2 = new ProductEntity();
        CategoryEntity cate = new CategoryEntity();
        pro1.setCategory(cate);
        pro2.setCategory(cate);
        listProduct.add(pro1);
        listProduct.add(pro2);
        Mockito.when(productRepo.findAll()).thenReturn(listProduct);
        Assertions.assertNotNull(listProduct);
        Assertions.assertEquals(2,productService.getAll().size());
    }

    @Test
    public void createProductSuccess_ReturnProductDTO(){
        ProductEntity entity = new ProductEntity();
        ProductDTO dto = new ProductDTO();
        CategoryEntity cate = new CategoryEntity();
        entity.setCategory(cate);
        Mockito.when(productRepo.save(Mockito.anyObject())).thenReturn(entity);
        Assertions.assertNotNull(productService.createProduct(dto));
    }

    @Test
    public void findByName_ReturnList(){
        List<ProductEntity> listProduct = new ArrayList<>();
        ProductEntity pro1 = new ProductEntity();
        CategoryEntity cate = new CategoryEntity();
        pro1.setCategory(cate);
        listProduct.add(pro1);
        Mockito.when(productRepo.findByProductNameContains(Mockito.anyString())).thenReturn(listProduct);
        Assertions.assertEquals(1,productService.findByName(Mockito.anyString()).size());

    }

    @Test
    public void findByCategory_ReturnList(){
        List<ProductEntity> listProduct = new ArrayList<>();
        ProductEntity pro1 = new ProductEntity();
        CategoryEntity cate = new CategoryEntity();
        pro1.setCategory(cate);
        listProduct.add(pro1);
        Mockito.when(productRepo.findByCategory(Mockito.anyObject())).thenReturn(listProduct);
        Assertions.assertEquals(1,productService.findByCategory(Mockito.anyString()).size());

    }
    @Test
    public void findByProductID_ReturnProduct(){
        ProductEntity entity = new ProductEntity();
        CategoryEntity cate = new CategoryEntity();
        entity.setCategory(cate);
        Mockito.when(productRepo.findByProductID(Mockito.anyString())).thenReturn(entity);
        Assertions.assertNotNull(productService.findByProductID(Mockito.anyString()));
    }

    @Test
    public void updateProduct_ReturnProduct(){
        ProductEntity entity = new ProductEntity();
        ProductDTO dto = new ProductDTO();
        CategoryEntity cate = new CategoryEntity();
        entity.setCategory(cate);
        Mockito.when(productRepo.save(Mockito.anyObject())).thenReturn(entity);
        Assertions.assertNotNull(productService.updateProduct(dto));
    }

    @Test
    public void deleteProduct_ReturnMessageSuccess(){
        ProductEntity entity = new ProductEntity();
        CategoryEntity cate = new CategoryEntity();
        entity.setCategory(cate);
        Mockito.when(productRepo.findByProductID(Mockito.anyString())).thenReturn(entity);
        Mockito.when(productRepo.save(Mockito.any())).thenReturn(entity);
        Assertions.assertEquals("Delete product success",productService.deleteProduct(Mockito.anyString()));
    }

    @Test
    public void deleteProduct_ReturnMessageFailed(){
        ProductEntity entity = new ProductEntity();
        CategoryEntity cate = new CategoryEntity();
        entity.setCategory(cate);
        Mockito.when(productRepo.findByProductID(Mockito.anyString())).thenReturn(null);

        Assertions.assertEquals("Delete product failed, product not found",productService.deleteProduct(Mockito.anyString()));
    }

}
