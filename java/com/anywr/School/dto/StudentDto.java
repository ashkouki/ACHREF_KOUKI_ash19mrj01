package com.anywr.School.dto;

import com.anywr.School.entities.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	

	private Long id;
    private String firstName;
    private String lastName;
    private String schoolClassName;
    
    private Long SchoolClassId;
    
    public StudentDto(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.schoolClassName = student.getSchoolClass().getName();
        this.SchoolClassId = student.getSchoolClass().getId();
    }
    
    
    public StudentDto(Student student, String className) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.schoolClassName = className;
        this.SchoolClassId = student.getSchoolClass().getId();
    }

}
