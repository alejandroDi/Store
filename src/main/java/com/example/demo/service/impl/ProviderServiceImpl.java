package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.model.Provider;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.repo.IProductRepo;
import com.example.demo.repo.IProviderRepo;
import com.example.demo.service.IProductService;
import com.example.demo.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    private final IProviderRepo repo;

    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }
}
