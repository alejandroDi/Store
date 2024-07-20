package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@IdClass(IngressDetailPK.class)
public class IngressDetail {
    @Id
    private Ingress ingress;
    @Id
    private Product product;

    static class IngresDetaikPK{

    }
}
