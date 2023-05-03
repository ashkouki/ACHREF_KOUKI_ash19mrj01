package com.anywr.School.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.School.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
