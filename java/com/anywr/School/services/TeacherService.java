package com.anywr.School.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anywr.School.dto.TeacherDto;
import com.anywr.School.entities.SchoolClass;
import com.anywr.School.entities.Teacher;
import com.anywr.School.repositories.SchoolClassRepository;
import com.anywr.School.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    @Autowired
    private SchoolClassRepository schoolClassRepository;
    
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
               
        .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id" + id));
        return convertToDto(teacher);
    }
    
    
    
    
    
    
    
    private TeacherDto convertToDto(Teacher teacher) {
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        if (teacher.getSchoolClass() != null) {
            //teacherDto.setClassName(teacher.getSchoolClass().getName());
        }
        return teacherDto;
    }
	
    
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = convertToEntity(teacherDto);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDto(savedTeacher);
    }
    
    
    private Teacher convertToEntity(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        return teacher;
    }
    

    	  public void deleteTeacher(Long id) {
    	        Optional<Teacher> teacher = teacherRepository.findById(id);
    	        teacher.ifPresent(t -> teacherRepository.delete(t));
    	    }
    
}
