package com.example.project1apione.models;

import com.example.project1apione.dto.CreateRequestDTO;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP not null default CURRENT_TIMESTAMP")
    private Instant date;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status", length = 15, insertable = false, columnDefinition = "varchar(15) default 'Pending'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managers;

    public Request(CreateRequestDTO requestDTO, Employee employee, Manager manager) {
        this.amount = requestDTO.getAmount();
        this.employees = employee;
        this.managers = manager;
    }
}