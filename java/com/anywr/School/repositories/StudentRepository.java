package com.anywr.School.repositories;


import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anywr.School.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	


    @Query("SELECT s AS student, c.name AS className FROM Student s JOIN s.schoolClass c WHERE c.name = :name")
    List<Object[]> findAllWithClassName(@Param("name") String name);

}
