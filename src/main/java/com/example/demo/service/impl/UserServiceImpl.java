package com.example.demo.service.impl;


import com.example.demo.model.User;
import com.example.demo.repo.IGenericRepo;

import com.example.demo.repo.IUserRepo;
import com.example.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
