package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.repo.IRoleRepo;
import com.example.demo.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}
