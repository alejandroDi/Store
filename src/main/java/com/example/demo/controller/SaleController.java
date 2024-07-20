package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.RoleDTO;
import com.example.demo.dto.SaleDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Sale;
import com.example.demo.service.IRoleService;
import com.example.demo.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {


    private final ISaleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>>  readAll() throws Exception {
        List<SaleDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Sale obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.save(covertToModel(dto));

        return new ResponseEntity<>(covertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created()
    }
    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto,@PathVariable("id") Integer id) throws Exception {
        Sale obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private SaleDTO covertToDTO(Sale obj){
        return modelMapper.map(obj,SaleDTO.class);
    }

    private Sale covertToModel(SaleDTO objDto){
        return modelMapper.map(objDto,Sale.class);
    }

}
