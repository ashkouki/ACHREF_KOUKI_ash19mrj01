package com.anywr.School.dto;

import com.anywr.School.entities.SchoolClass;
import com.anywr.School.entities.Student;

public class StudentMapper {
	
	

	    public static StudentDto toDto(Student student) {
	        StudentDto dto = new StudentDto();
	        dto.setId(student.getId());
	        dto.setFirstName(student.getFirstName());
	        dto.setLastName(student.getLastName());
	        dto.setSchoolClassName(student.getSchoolClass().getName());
	        dto.setSchoolClassId(student.getSchoolClass().getId());
	        return dto;
	    }

	    public static Student toEntity(StudentDto dto) {
	        Student student = new Student();
	        student.setId(dto.getId());
	        student.setFirstName(dto.getFirstName());
	        student.setLastName(dto.getLastName());
	        SchoolClass schoolClass = new SchoolClass();
	        schoolClass.setId(dto.getSchoolClassId());
	        schoolClass.setName(dto.getSchoolClassName());
	        student.setSchoolClass(schoolClass);
	        return student;
	    
	}


}
