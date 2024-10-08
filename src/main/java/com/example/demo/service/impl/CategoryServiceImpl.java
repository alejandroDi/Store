package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repo.ICategoryRepo;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category,Integer> implements ICategoryService {


    private final ICategoryRepo repo;


    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByNameService(String name) {
        return repo.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameAndEnabledService(String name, boolean enabled) {
        return repo.findByNameOrEnabled(name,enabled);
    }

    @Override
    public List<Category> getNameAndDescriptionService(String name, String description) {
        return repo.getNameAndDescription2(name,description);
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction,"name"));
    }


}
