package com.example.project1apione.dao;

import com.example.project1apione.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    public Optional<Request> getByDate(String date);

    public List<Request> getAllByEmployees_Id(Integer emp_id);
}
