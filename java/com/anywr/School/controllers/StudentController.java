package com.anywr.School.controllers;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anywr.School.dto.StudentDto;
import com.anywr.School.entities.Student;
import com.anywr.School.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
    private StudentService studentService;

	
	
	@GetMapping("/pagination")
    public ResponseEntity<Page<StudentDto>> getAllStudentsWithPagination(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<StudentDto> page = studentService.findPaginated(pageNo, pageSize, sortBy);

        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(page);
        }
    }
 
    @GetMapping("")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> studentDtos = studentService.findAll();
        
        if (studentDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
          
        
            return new ResponseEntity<>(studentDtos, HttpStatus.OK);
        }}



    @PostMapping("")
    public StudentDto addStudent(@RequestBody StudentDto studentDTO) {
        return studentService.addStudent(studentDTO);
    }

  
   
    @DeleteMapping("/{id}")
    public void DeleteStudent(@PathVariable Long id) {
    	studentService.deleteStudent(id);
    }
    
    @GetMapping("/classname/{name}")
    public ResponseEntity<Page<StudentDto>> getStudentsWithClassName(@PathVariable String name,@RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy) {
    	
    	Page<StudentDto> students = studentService.getStudentsWithClassName(name,pageNo, pageSize, sortBy);
        //return new ResponseEntity<>(students, HttpStatus.OK);
    	Page<StudentDto> page = studentService.findPaginated(pageNo, pageSize, sortBy);
        
        if (students.isEmpty()) {
        	return ResponseEntity.ok(page);
        } else {
            return ResponseEntity.ok(students);
        }
        
        
    }
    
    

    
}
