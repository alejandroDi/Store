package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.repo.IProductRepo;
import com.example.demo.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    private final IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }
}
