package com.example.ers.entities.repos;

import com.example.ers.entities.ReimbsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReimbsUserRepository extends JpaRepository<ReimbsUser, Long> {
    Optional<ReimbsUser> findByEmail(String email);
}