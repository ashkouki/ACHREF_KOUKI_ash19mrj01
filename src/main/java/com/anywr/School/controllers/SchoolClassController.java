package com.anywr.School.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anywr.School.dto.SchoolClassDto;
import com.anywr.School.services.SchoolClassService;

@RestController
@RequestMapping("/school-classes")
public class SchoolClassController {
	
	@Autowired
    private SchoolClassService schoolClassService;
    
    @GetMapping("")
    public List<SchoolClassDto> getAllSchoolClasses() {
        return schoolClassService.getAllSchoolClasses();
    }
    
    @GetMapping("/{id}")
    public SchoolClassDto getSchoolClassById(@PathVariable Long id) {
        return schoolClassService.getSchoolClassById(id);
    }
    
    
    @PostMapping("")
    public SchoolClassDto createSchoolClass(@RequestBody  SchoolClassDto schoolClassDto) {
        return schoolClassService.createSchoolClass(schoolClassDto);

    }
    

    @DeleteMapping("/{id}")
    public void DeleteSchoolClasse(@PathVariable Long id) {
    	schoolClassService.deleteSchoolClass(id);
    }
    
}
