package com.example.SpringBootTMS.work46.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account",unique = true)
    private String account;
    @Column(name="sum")
    private Integer sum;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
}
