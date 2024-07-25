package com.example.demo.controller;

import com.example.demo.dto.*;
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
import java.util.Map;

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

    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume1(){
        return new ResponseEntity<>(service.callProcedure1(),HttpStatus.OK);
    }

    @GetMapping("/resume2")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume2(){
        return new ResponseEntity<>(service.callProcedure2(),HttpStatus.OK);
    }
    @GetMapping("/resume3")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume3(){
        return new ResponseEntity<>(service.callProcedure3(),HttpStatus.OK);
    }
    @GetMapping("/resume4")
    public ResponseEntity<Void> getSaleResume4(){
        service.callProcedure4();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mostExpensive")
    public ResponseEntity<SaleDTO> getMostExpensive(){
        SaleDTO dto = covertToDTO(service.getSaleMontExpensive());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/bestSeller")
    public ResponseEntity<String> getBestSeller(){
        String user = service.getBestSellerPerson();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/sellerCount")
    public ResponseEntity<Map<String,Long>> getSellerCount(){
        Map<String,Long> user= service.getSalesCountBySeller();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/bestProduct")
    public ResponseEntity<Map<String,Double>> getBestProduct(){
        Map<String,Double> product= service.getProductBestSeller();
        return new ResponseEntity<>(product,HttpStatus.OK);
    }



    private SaleDTO covertToDTO(Sale obj){
        return modelMapper.map(obj,SaleDTO.class);
    }

    private Sale covertToModel(SaleDTO objDto){
        return modelMapper.map(objDto,Sale.class);
    }

}
