package com.anywr.School.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/{id}")
    public TeacherDto getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }
    
    
	
}
