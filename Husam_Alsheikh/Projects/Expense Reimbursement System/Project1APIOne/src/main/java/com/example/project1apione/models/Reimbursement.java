package com.example.project1apione.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reimbursements")
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP not null default CURRENT_TIMESTAMP")
    private Instant date;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request requests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managers;

    public Reimbursement(Double amount, Request requests, Employee employees, Manager managers) {
        this.amount = amount;
        this.requests = requests;
        this.employees = employees;
        this.managers = managers;
    }
}