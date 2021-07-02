package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.mapper.ProductMapper;
import com.nashtech.hanashop.repository.ProductRepository;
import com.nashtech.hanashop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepo;

    public List<ProductDTO> getAll(){
        List<ProductDTO> listProduct = new ArrayList<>();
        List<ProductEntity> listEntity = productRepo.findAll();
        listEntity.forEach(entity -> {
            listProduct.add(ProductMapper.parseEntityToDTO(entity));
        });
        return listProduct;
    }

    public ProductDTO createProduct(ProductDTO dto){
        dto.setProductID(randomProductID(dto));
        Date date = new Date();
        dto.setCreateDate(date);
        dto.setStatus(true);
        dto.setUpdateDate(null);

        ProductEntity productEntity = ProductMapper.parseDTOToEntity(dto);
        ProductEntity productEntityNew = productRepo.save(productEntity);
        return ProductMapper.parseEntityToDTO(productEntityNew);
    }

    public String deleteProduct(String productID){
        ProductEntity entity = productRepo.findByProductID(productID);
        if(entity!=null){
//            productRepo.delete(entity);
            entity.setStatus(false);
            productRepo.save(entity);
            return "Delete product success";
        }
        return "Delete product failed";
    }
    public ProductDTO updateProduct(ProductDTO dto){
        Date date = new Date();
        dto.setUpdateDate(date);
        ProductEntity entity = ProductMapper.parseDTOToEntity(dto);
        ProductEntity newEntity = productRepo.save(entity);
        return ProductMapper.parseEntityToDTO(newEntity);
    }


    private String randomProductID(ProductDTO dto){
        Random random = new Random();
        String productID = "";
        while (true){
            productID = dto.getCategoryID() + random.nextInt(100000);
            ProductEntity entity = productRepo.findByProductID(dto.getProductID());
            if(entity==null) {
                break;
            }
        }
        return productID;
    }


}
