package com.example.SpringBootTMS.work49.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "number_group")
    private String group;
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_payment_id")
    private CoursePayment coursePayment;
}
