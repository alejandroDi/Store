package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ClientDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Client;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {


    private final IClientService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>>  readAll() throws Exception {
        List<ClientDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Client obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) throws Exception {
        Client obj = service.save(covertToModel(dto));
        return new ResponseEntity<>(covertToDTO(obj), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto,@PathVariable("id") Integer id) throws Exception {
        Client obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ClientDTO covertToDTO(Client obj){
        return modelMapper.map(obj,ClientDTO.class);
    }

    private Client covertToModel(ClientDTO objDto){
        return modelMapper.map(objDto,Client.class);
    }

}
