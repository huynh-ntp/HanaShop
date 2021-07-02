package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.CategoryDTO;
import com.nashtech.hanashop.data.entity.CategoryEntity;
import com.nashtech.hanashop.data.mapper.CategoryMapper;
import com.nashtech.hanashop.repository.CategoryRepository;
import com.nashtech.hanashop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepo;

    public List<CategoryDTO> getAll(){
        List<CategoryDTO> listCateDTO = new ArrayList<>();
        List<CategoryEntity> listCateEn = categoryRepo.findAll();
        listCateEn.forEach(entity -> {
            listCateDTO.add(CategoryMapper.parseEntityToDTO(entity));
        });
        return listCateDTO;
    }
}
