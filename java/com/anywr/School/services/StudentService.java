package com.anywr.School.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.anywr.School.dto.StudentDto;
import com.anywr.School.entities.SchoolClass;
import com.anywr.School.entities.Student;
import com.anywr.School.repositories.SchoolClassRepository;
import com.anywr.School.repositories.StudentRepository;


@Service
public class StudentService {
	
	@Autowired
     StudentRepository studentRepository;

	 @Autowired
	     SchoolClassRepository schoolClassRepository;
  
    @Autowired
     ModelMapper modelMapper;



    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
    }

    
    public void deleteStudent(Long id) {
    	studentRepository.deleteById(id);
    }
     
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Long schoolClassId = studentDto.getSchoolClassId();
        SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId).orElseThrow(() -> new IllegalArgumentException("Invalid school class id: " + schoolClassId));
        student.setSchoolClass(schoolClass);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
   
    
    
    
    public StudentDto findById(Long id) {
        
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        studentDto.setSchoolClassName(student.getSchoolClass().getName());
        
        // Return the studentDto
        return studentDto;
    }
    
 
    public List<StudentDto> getStudentsWithClassName( String name) {
        List<Object[]> studentsWithClassName = studentRepository.findAllWithClassName(name);
        List<StudentDto> studentDtos = new ArrayList<>();

        if (studentsWithClassName.isEmpty()) {
           
            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                studentDtos.add(new StudentDto(student));
            }
        } else {
            for (Object[] studentWithClassName : studentsWithClassName) {
                Student student = (Student) studentWithClassName[0];
                String schoolClassName = (String) studentWithClassName[1];
                StudentDto studentDto = new StudentDto(student);
                studentDtos.add(studentDto);
            }
        }
        return studentDtos;
    }
    
}
