package com.example.testingdemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "value")
    private String value;

    public Model() {
    }

    public Model(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
