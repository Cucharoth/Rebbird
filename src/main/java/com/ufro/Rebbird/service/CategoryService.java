package com.ufro.Rebbird.service;

import org.springframework.stereotype.Service;

import com.ufro.Rebbird.model.Category;
import com.ufro.Rebbird.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findCategoryByid(int id){
        return categoryRepository.findById(id).orElse(null);
    }
}
