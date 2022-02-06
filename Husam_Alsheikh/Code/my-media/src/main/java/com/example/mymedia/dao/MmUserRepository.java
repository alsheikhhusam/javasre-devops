package com.example.mymedia.dao;

import com.example.mymedia.models.MmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MmUserRepository extends JpaRepository<MmUser, Integer> {

}
