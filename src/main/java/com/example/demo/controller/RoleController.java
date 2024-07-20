package com.example.demo.controller;

import com.example.demo.dto.RoleDTO;
import com.example.demo.model.Role;
import com.example.demo.service.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {


    private final IRoleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>>  readAll() throws Exception {
        List<RoleDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Role obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) throws Exception {
        Role obj = service.save(covertToModel(dto));
        return new ResponseEntity<>(covertToDTO(obj), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto,@PathVariable("id") Integer id) throws Exception {
        Role obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private RoleDTO covertToDTO(Role obj){
        return modelMapper.map(obj,RoleDTO.class);
    }

    private Role covertToModel(RoleDTO objDto){
        return modelMapper.map(objDto,Role.class);
    }

}
