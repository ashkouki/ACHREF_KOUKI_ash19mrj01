package com.anywr.School.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anywr.School.entities.Teacher;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
