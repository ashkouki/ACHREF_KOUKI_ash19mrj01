package com.anywr.School.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import com.anywr.School.dto.StudentMapper;  
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
   
 
  

    
    public Page<StudentDto> findPaginated(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Student> page = studentRepository.findAll(pageable);
        return page.map(StudentMapper::toDto);
    }
   
    
 
    public Page<StudentDto> getStudentsWithClassName( String name,int pageNo, int pageSize, String sortBy) {
    	 Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Object[]> studentsWithClassName = studentRepository.findAllWithClassName(name,pageable);
        //List<StudentDto> studentDtos = new ArrayList<>();
      
            for (Object[] studentWithClassName : studentsWithClassName) {
                Student student = (Student) studentWithClassName[0];
                String schoolClassName = (String) studentWithClassName[1];
                StudentDto studentDto = new StudentDto(student);
                //studentDtos.add(studentDto);
            }
        
            return studentsWithClassName.map(object -> new StudentDto(
                    (Student) object[0],
                    (String) object[1]
            ));
    }
    
}
