package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
public class RestTemplateController {
    private final RestTemplate restTemplate;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<?> getPokemon(@PathVariable("name") String name){
        String pokemonUrl ="https://pokeapi.co/api/v2/pokemon/" + name;
        String response = restTemplate.getForEntity(pokemonUrl,String.class).getBody();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test1")
    public ResponseEntity<List<CategoryDTO>> test1(){
        String url = "http://localhost:8086/categories";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<CategoryDTO>> typeRef = new ParameterizedTypeReference<>() {};
        return restTemplate.exchange(url, HttpMethod.GET,entity,typeRef);
    }
}
