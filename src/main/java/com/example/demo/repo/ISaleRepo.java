package com.example.demo.repo;

import com.example.demo.dto.IProcedureDTO;
import com.example.demo.dto.ProcedureDTO;
import com.example.demo.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale, Integer> {

    //forma personalisable
    @Query(value = "select *from fn_sales()",nativeQuery = true)
    List<Object[]> callProcedure1();

    //forma rapida sin editar solo mostrar
    @Query(value = "select *from fn_sales()",nativeQuery = true)
    List<IProcedureDTO> callProcedure2();


    //metodo largo y casi no se usa
    @Query(name = "Sale.fn_sales",nativeQuery = true)
    List<ProcedureDTO> callProcedure3();

    @Procedure(procedureName = "pr_sales")
    void callProcedure4();
}
