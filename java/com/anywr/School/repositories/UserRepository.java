package com.anywr.School.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.anywr.School.entities.UserDao;


@Repository
public interface UserRepository extends JpaRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}