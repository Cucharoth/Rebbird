package com.ufro.Rebbird.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ufro.Rebbird.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
}
