package com.example.demo.repo;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {
    //DeliveredQueries

    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);

    //%XYZ%  -> findByNameContains
    //%XYZ   -> findByNameStarsWhith
    //XYZ%   -> findByNameEndsWhith


    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> findByNameOrEnabled(String name, boolean enabled);

    List<Category> findByEnabled(boolean enabled);

    List<Category> findByEnabledTrue();

    List<Category> findByEnabledFalse();

    Category findOneByName(String name);

    List<Category> findTop3ByName(String name);

    List<Category> findByNameIs(String name);

    List<Category> findByNameIsNot(String name);

    List<Category> findByNameIsNull();

    List<Category> findByNameIsNotNull();

    List<Category> findByNameEqualsIgnoreCase(String name);

    List<Category> findByIdCategoryLessThan(Integer id);

    List<Category> findByIdCategoryLessThanEqual(Integer id);

    List<Category> findByIdCategoryGreaterThan(Integer id);

    List<Category> findByIdCategoryGreaterThanEqual(Integer id);

    List<Category> findByIdCategoryBetween(Integer id,Integer id2);

    List<Category> findByNameOrderByDescription(String name);

    List<Category> findByNameOrderByDescriptionAsc(String name);

    //JPQL JAVA PERSISTENSE QUERY LENGUANGE

    /*@Query("FROM Products p WHERE p.category.name = :name") //join*/
    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> getNameAndDescription1(@Param("name") String name, @Param("description") String description);

    //tambien acepta paquetes si tenemos multiples entidades @Query("SELECT new com.example.demo.model.Category (c.name,c.enabled) FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    @Query("SELECT new Category (c.name,c.enabled) FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> getNameAndDescription2(@Param("name") String name, @Param("description") String description);

    ///Native query

    @Query(value = "SELECT * FROM category c WHERE c.name =:name",nativeQuery = true)
    List<Category> getNameSQL(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE category SET name = :name",nativeQuery = true)
    Integer updateName(@Param("name")String name);
}
