package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false,foreignKey = @ForeignKey(name = "FK_CLIENT_SALE"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false,foreignKey = @ForeignKey(name = "FK_USER_SALE"))
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(6,2)",nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(6,2)",nullable = false)
    private double tax;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL)
    private List<SaleDetail> details;
}
