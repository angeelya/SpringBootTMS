package com.example.SpringBootTMS.work43;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="customer_name")
    private String customerName;
    @Column(name = "address")
    private String address;
    @Column(name="type_product")
    private String typeProduct;
    @Column(name = "cost")
    private Integer cost;

}
