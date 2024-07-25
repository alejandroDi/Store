package com.example.demo.service.impl;

import com.example.demo.dto.IProcedureDTO;
import com.example.demo.dto.ProcedureDTO;
import com.example.demo.model.Sale;
import com.example.demo.model.SaleDetail;
import com.example.demo.repo.IGenericRepo;
import com.example.demo.repo.ISaleRepo;
import com.example.demo.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.FlashMap;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<ProcedureDTO> callProcedure1() {
        List<ProcedureDTO> list = new ArrayList<>();

        repo.callProcedure1().forEach(e ->{
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityfn((Integer) e[0]);
            dto.setDatetimefn((String) e[1]);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Override
    public List<ProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }

    @Override
    public void callProcedure4() {
        repo.callProcedure4();
    }

    @Override
    public Sale getSaleMontExpensive() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        Map<String,Double> byUser = repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(),summingDouble(Sale::getTotal)));

        return Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        return repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(),counting()));
    }


    //llegar de sales a salesDetail
    @Override
    public Map<String, Double> getProductBestSeller() {
        Stream<Sale> saleStream = repo.findAll().stream();
        Stream<List<SaleDetail>> lsStream = saleStream.map(Sale::getDetails);
        Stream<SaleDetail> streamDetail = lsStream.flatMap(Collection::stream); //list -> list.stream()
        Map<String,Double> bestProd = streamDetail
                .collect(groupingBy( d -> d.getProduct().getName(), summingDouble(SaleDetail::getQuantity)));

        return bestProd.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue,newValue) -> oldValue,LinkedHashMap::new
                ));

    }
}
