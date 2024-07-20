package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.RoleDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final IUserService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>>  readAll() throws Exception {
        List<UserDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception {
        User obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<UserDTO>> create(@Valid @RequestBody UserDTO dto) throws Exception {
        User obj = service.save(covertToModel(dto));
        return new ResponseEntity<>(new GenericResponse<>(201,"succes", Arrays.asList(covertToDTO(obj))), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto,@PathVariable("id") Integer id) throws Exception {
        User obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UserDTO covertToDTO(User obj){
        return modelMapper.map(obj,UserDTO.class);
    }

    private User covertToModel(UserDTO objDto){
        return modelMapper.map(objDto,User.class);
    }

}
