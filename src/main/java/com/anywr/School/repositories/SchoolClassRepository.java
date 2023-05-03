package com.anywr.School.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.School.entities.SchoolClass;



public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

}
