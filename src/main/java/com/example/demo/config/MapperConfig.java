package com.example.demo.config;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        ModelMapper mapper = new ModelMapper();

        //Handle Missmatches

        //Lectura
        TypeMap<Category, CategoryDTO> typeMap1 = mapper.createTypeMap(Category.class, CategoryDTO.class);
        typeMap1.addMapping(Category::getName, (dest, v)-> dest.setNameofCategory((String) v));

        //Escritura
        TypeMap<CategoryDTO, Category> typeMap2 = mapper.createTypeMap(CategoryDTO.class, Category.class);
        typeMap2.addMapping(CategoryDTO::getNameofCategory, (dest, v)-> dest.setName((String) v));

        return mapper;
    }

    @Bean("productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();

        /*TypeMap<ProductDTO, Product> typeMap1 = mapper.createTypeMap(ProductDTO.class, Product.class);
        typeMap1.addMapping(ProductDTO::getIdCategoria, (dest, v)-> dest.getCategory().setIdCategory((Integer) v));

        TypeMap<Product, ProductDTO> typeMap2 = mapper.createTypeMap(Product.class, ProductDTO.class);
        typeMap2.addMapping(e -> e.getCategory().getIdCategory(), (dest, v)-> dest.setIdCategoria((Integer) v));*/

        return mapper;
    }


    @Bean("defaultMapper")
    public ModelMapper defaultMapper(){
        return new ModelMapper();
    }
}
