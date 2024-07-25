package com.example.demo.service;

import com.example.demo.dto.IProcedureDTO;
import com.example.demo.dto.ProcedureDTO;
import com.example.demo.model.Sale;

import java.util.List;
import java.util.Map;

public interface ISaleService extends ICRUD<Sale,Integer> {
    List<ProcedureDTO> callProcedure1();

    List<IProcedureDTO> callProcedure2();

    List<ProcedureDTO> callProcedure3();

    void callProcedure4();

    Sale getSaleMontExpensive();//obtener venta mayor

    String getBestSellerPerson(); //nombre mejor vendedor

    Map<String,Long> getSalesCountBySeller(); //contar cantidad de ventas por vendedor
    Map<String,Double> getProductBestSeller(); //contar cantidad de ventas por vendedor

    //video 36

}
