package com.example.testingdemo.repos;

import com.example.testingdemo.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findAllByValue(String value);
    List<Model> findAllByValueLike(String value);
    @Query(nativeQuery = true, value = "select count(*) from models where value like :query")
    Long countAllByValueLike(@Param("query") String query);
}
