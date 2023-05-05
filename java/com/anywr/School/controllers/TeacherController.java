package com.anywr.School.controllers;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anywr.School.dto.StudentDto;
import com.anywr.School.dto.TeacherDto;
import com.anywr.School.services.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	
	@Autowired
    private TeacherService teacherService;

	@GetMapping("")
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
    

	
	
	@GetMapping("{id}")
	public ResponseEntity<TeacherDto> TeacherDto(@PathVariable Long id) {
	 
		try {
		Optional<TeacherDto> optionalTeacherDto = Optional.of(teacherService.getTeacherById(id));
		if (optionalTeacherDto.isPresent()) {
		    
		    return new ResponseEntity<>(optionalTeacherDto.get(),HttpStatus.CREATED);
		   
		} else {
	
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
		}
		
		catch(Exception e) {
			
			  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			  
		}
		}
    
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
    
    
    @PostMapping("")
    public TeacherDto addStudent(@RequestBody TeacherDto teacherDto) {
        return teacherService.createTeacher(teacherDto);
    }
    
	
}
