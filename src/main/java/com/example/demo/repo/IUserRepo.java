package com.example.demo.repo;

import com.example.demo.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer> {
    //from User
    User findOneByUsername(String username);
}
