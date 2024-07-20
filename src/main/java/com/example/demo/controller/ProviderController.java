package com.example.demo.controller;

import com.example.demo.dto.ProviderDTO;
import com.example.demo.model.Provider;
import com.example.demo.service.IProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {


    private final IProviderService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>>  readAll() throws Exception {
        List<ProviderDTO> list = service.findAll().stream().map(this::covertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception {
        Provider obj = service.readById(id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception {
        Provider obj = service.save(covertToModel(dto));
        return new ResponseEntity<>(covertToDTO(obj), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto,@PathVariable("id") Integer id) throws Exception {
        Provider obj = service.update(covertToModel(dto), id);
        return ResponseEntity.ok(covertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProviderDTO covertToDTO(Provider obj){
        return modelMapper.map(obj,ProviderDTO.class);
    }

    private Provider covertToModel(ProviderDTO objDto){
        return modelMapper.map(objDto,Provider.class);
    }

}
