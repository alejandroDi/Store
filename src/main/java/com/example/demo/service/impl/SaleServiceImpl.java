package com.example.demo.service.impl;

import com.example.demo.model.Sale;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.repo.ISaleRepo;
import com.example.demo.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }
}
