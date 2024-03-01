package com.example.SpringBootTMS.work46.model;

import com.example.SpringBootTMS.work46.model.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="login",unique = true)
    private String login;
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    List<Card> cardList;

}
