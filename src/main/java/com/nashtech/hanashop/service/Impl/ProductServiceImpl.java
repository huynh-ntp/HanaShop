package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.dto.ProductDTO;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.mapper.CategoryMapper;
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

    private final ProductMapper mapper = new ProductMapper();

    public List<ProductDTO> getAll(){
        List<ProductDTO> listProduct = new ArrayList<>();
        List<ProductEntity> listEntity = productRepo.findAll();
        listEntity.forEach(entity -> {
            listProduct.add(mapper.parseEntityToDTO(entity));
        });
        return listProduct;
    }

    public ProductDTO createProduct(ProductDTO dto){
        dto.setProductID(randomProductID(dto));
        Date date = new Date();
        dto.setCreateDate(date);
        dto.setStatus(true);
        dto.setUpdateDate(null);

        ProductEntity productEntity = mapper.parseDTOToEntity(dto);
        ProductEntity productEntityNew = productRepo.save(productEntity);
        return mapper.parseEntityToDTO(productEntityNew);

    }

    public String deleteProduct(String productID){
        ProductEntity entity = productRepo.findByProductID(productID);
        if(entity!=null){
//            productRepo.delete(entity);
            entity.setStatus(false);
            productRepo.save(entity);
            return "Delete product success";
        }
        return "Delete product failed, product not found";
    }
    public ProductDTO updateProduct(ProductDTO dto){
        if(productRepo.findByProductID(dto.getProductID())==null){
            return null;
        }
        Date date = new Date();
        dto.setUpdateDate(date);
        ProductEntity entity = mapper.parseDTOToEntity(dto);
        ProductEntity newEntity = productRepo.save(entity);
        return mapper.parseEntityToDTO(newEntity);
    }

    @Override
    public List<ProductDTO> findByName(String productName) {
        List<ProductEntity> listEntity = productRepo.findByProductNameContains(productName);
        List<ProductDTO> listDto = new ArrayList<>();
        if(listEntity!=null){
            listEntity.forEach(entity -> {
                listDto.add(mapper.parseEntityToDTO(entity));
            });
        }
        return listDto;
    }

    @Override
    public List<ProductDTO> findByCategory(String categoryID) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryID(categoryID);
        List<ProductEntity> listEntity = productRepo.findByCategory(CategoryMapper.parseDTOToEntity(categoryDTO));
        List<ProductDTO> listDto = new ArrayList<>();
        if(listEntity!=null){
            listEntity.forEach(entity -> {
                listDto.add(mapper.parseEntityToDTO(entity));
            });
        }
        return listDto;
    }

    @Override
    public ProductDTO findByProductID(String productID) {
        ProductEntity entity = productRepo.findByProductID(productID);
        if(entity!=null){
            return mapper.parseEntityToDTO(entity);
        }
        return null;

    }

    @Override
    public List<ProductDTO> findByNameAndCategory(String name, String categoryID) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryID(categoryID);
        System.out.println(name);
        System.out.println(categoryID);
        List<ProductEntity> listEntity = productRepo.findByProductNameContainsAndCategory
                (name,CategoryMapper.parseDTOToEntity(categoryDTO));
        List<ProductDTO> listDto = new ArrayList<>();
        if(listEntity!=null){
            listEntity.forEach(entity -> {
                listDto.add(mapper.parseEntityToDTO(entity));
            });
        }
        return listDto;
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
