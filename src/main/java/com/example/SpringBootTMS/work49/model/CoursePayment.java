package com.example.SpringBootTMS.work49.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sum")
    private Integer sum;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @JsonIgnore
    @OneToOne(optional = false, mappedBy = "coursePayment")
    private Student student;
}
