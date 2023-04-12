package com.example.demo.repository;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User,Integer> {

//    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    String findPassword(String email);

     Optional<User> findUserByUsername(String username);
}
