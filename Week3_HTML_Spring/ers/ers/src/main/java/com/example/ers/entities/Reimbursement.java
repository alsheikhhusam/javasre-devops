package com.example.ers.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reimb", schema = "reimbs")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    ReimbType type;

    @Column(name = "amount")
    Float amount;

    @Enumerated(EnumType.STRING)
    ReimbStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_user_id")
    private ReimbsUser employee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "man_user_id")
    private ReimbsUser manager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Reimbursement that = (Reimbursement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
