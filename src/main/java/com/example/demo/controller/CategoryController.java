package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.GenericResponse;
import com.example.demo.model.Category;
import com.example.demo.service.ICategoryService;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final ICategoryService service;
    @Qualifier("categoryMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>>  readAll() throws Exception {
        List<CategoryDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Category obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<CategoryDTO>> create(@Valid @RequestBody CategoryDTO dto) throws Exception {
        Category obj = service.save(covertToModel(dto));
        return ResponseEntity.ok(new GenericResponse<>(201,"succes", Arrays.asList( covertToDTO(obj))));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto,@PathVariable("id") Integer id) throws Exception {
        Category obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //////Queries
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name) throws Exception {
        List<CategoryDTO> list = service.findByNameService(name).stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/find/name/{name}/{enabled}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name,@PathVariable("enabled") boolean enabled) throws Exception {
        List<CategoryDTO> list = service.findByNameAndEnabledService(name,enabled).stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    ////JPQA
    @GetMapping("/find/name/description")
    public ResponseEntity<List<CategoryDTO>> getNameAndDescriptionService(@RequestParam("name") String name,@RequestParam("description") String description) throws Exception {
        List<CategoryDTO> list = service.getNameAndDescriptionService(name,description).stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    private CategoryDTO covertToDTO(Category obj){
        return modelMapper.map(obj,CategoryDTO.class);
    }

    private Category covertToModel(CategoryDTO objDto){
        return modelMapper.map(objDto,Category.class);
    }

}
