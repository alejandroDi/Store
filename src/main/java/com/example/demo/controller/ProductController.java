package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final IProductService service;
    @Qualifier("productMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>>  readAll() throws Exception {
        List<ProductDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Product obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = service.save(covertToModel(dto));
        return new ResponseEntity<>(covertToDTO(obj), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto,@PathVariable("id") Integer id) throws Exception {
        Product obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProductDTO covertToDTO(Product obj){
        return modelMapper.map(obj,ProductDTO.class);
    }

    private Product covertToModel(ProductDTO objDto){
        return modelMapper.map(objDto,Product.class);
    }

}
