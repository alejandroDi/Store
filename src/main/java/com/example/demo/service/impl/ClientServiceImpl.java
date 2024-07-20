package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Client;
import com.example.demo.repo.ICategoryRepo;
import com.example.demo.repo.IClientRepo;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client,Integer> implements IClientService {


    private final IClientRepo repo;


    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}
