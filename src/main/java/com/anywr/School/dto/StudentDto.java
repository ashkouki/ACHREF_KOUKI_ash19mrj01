package com.anywr.School.dto;

import com.anywr.School.entities.SchoolClass;
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
		
}
