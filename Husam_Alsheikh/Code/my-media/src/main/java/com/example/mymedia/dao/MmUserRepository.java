package com.example.mymedia.dao;

import com.example.mymedia.models.MmRole;
import com.example.mymedia.models.MmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MmUserRepository extends JpaRepository<MmUser, Integer> {
    Optional<MmUser> findMmUserByUsername(String username);

    Integer countMmUserByRole(MmRole role);

    @Query("From MmUser u where u.profile.email = :email") // JPAQL
//    @Query(value="", nativeQuery = true) // Native SQL
    MmUser getUserByProfileEmail(@Param("email") String email);
}
