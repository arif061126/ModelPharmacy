package com.modelpharmacy.repository;

import com.modelpharmacy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByNIDNumber(String nidNumber);

    //@Query("SELECT DISTINCT u FROM User u WHERE u.userName = :userName")
    Optional<User> findUserByUserName(String userName);
}
