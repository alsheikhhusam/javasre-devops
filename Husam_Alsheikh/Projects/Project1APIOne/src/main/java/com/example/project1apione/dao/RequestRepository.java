package com.example.project1apione.dao;

import com.example.project1apione.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    public List<Request> getAllByEmployees_Id(Integer emp_id);
}
