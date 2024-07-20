package com.example.demo.service.impl;

import com.example.demo.exception.ModelNotFoundException;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    protected abstract IGenericRepo<T,ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID " + id + " not found"));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID " + id + " not found"));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID " + id + " not found"));
        getRepo().deleteById(id);
    }
}
