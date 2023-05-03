package com.anywr.School.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.School.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
