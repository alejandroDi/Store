package com.example.demo.service;

import com.example.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService extends ICRUD<Category,Integer> {

    List<Category> findByNameService(String name);

    List<Category> findByNameAndEnabledService(String name,boolean enabled);

    List<Category> getNameAndDescriptionService(String name, String description);

    Page<Category> findPage(Pageable pageable);

    List<Category> findAllOrder(String param);
}
