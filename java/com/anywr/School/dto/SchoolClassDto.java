package com.anywr.School.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDto {

	
private Long id;
	
    private String name;
	
    private TeacherDto teacher;
    
    
    public void setTeacher(TeacherDto teacherDto) {
        this.teacher = teacherDto;
		
	}
}

