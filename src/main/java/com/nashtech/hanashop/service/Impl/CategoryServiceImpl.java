package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.entity.CategoryEntity;
import com.nashtech.hanashop.data.mapper.CategoryMapper;
import com.nashtech.hanashop.repository.CategoryRepository;
import com.nashtech.hanashop.repository.ProductRepository;
import com.nashtech.hanashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductRepository productRepo;
    public List<CategoryDTO> getAll(){
        List<CategoryDTO> listCateDTO = new ArrayList<>();
        List<CategoryEntity> listCateEn = categoryRepo.findAll();
        listCateEn.forEach(entity -> {
            listCateDTO.add(CategoryMapper.parseEntityToDTO(entity));
        });
        return listCateDTO;
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        if(categoryRepo.existsByCategoryID(categoryDTO.getCategoryID().trim())){
            return null;
        }
        categoryRepo.save(CategoryMapper.parseDTOToEntity(categoryDTO));
        return categoryDTO;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        if(!categoryRepo.existsByCategoryID(categoryDTO.getCategoryID().trim())){
            return null;
        }
        categoryRepo.save(CategoryMapper.parseDTOToEntity(categoryDTO));
        return categoryDTO;
    }

    @Override
    public CategoryDTO findByCategoryID(String categoryID) {
        CategoryEntity entity = categoryRepo.findByCategoryID(categoryID);
        if(entity==null){
            return null;
        }
        return CategoryMapper.parseEntityToDTO(entity);
    }

    @Override
    public String delete(String categoryID) {
        CategoryEntity entity = categoryRepo.findByCategoryID(categoryID);
        if(productRepo.findByCategory(entity).size()!=0){
            return "Can not delete category!";
        }
        categoryRepo.delete(entity);
        return "Delete success";

    }
}
