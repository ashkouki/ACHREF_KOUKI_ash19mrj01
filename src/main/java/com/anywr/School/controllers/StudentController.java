package com.anywr.School.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anywr.School.dto.StudentDto;
import com.anywr.School.entities.Student;
import com.anywr.School.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<StudentDto> getAllStudents() {
        return studentService.findAll();
    }

    @PostMapping("")
    public StudentDto addStudent(@RequestBody StudentDto studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentbyID(@PathVariable Long id) {
        return  studentService.findById(id);
    }
   
    @DeleteMapping("/{id}")
    public void DeleteStudent(@PathVariable Long id) {
    	studentService.deleteStudent(id);
    }
}
