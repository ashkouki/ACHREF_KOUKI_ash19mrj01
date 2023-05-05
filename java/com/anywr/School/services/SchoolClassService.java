package com.anywr.School.services;

import java.util.List;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.anywr.School.dto.SchoolClassDto;
import com.anywr.School.entities.SchoolClass;
import com.anywr.School.repositories.SchoolClassRepository;

@Service
public class SchoolClassService {
	
	@Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    
    public List<SchoolClassDto> getAllSchoolClasses() {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        return schoolClasses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public SchoolClassDto getSchoolClassById(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School class not found"));
        return convertToDto(schoolClass);
    }
 
    
    public SchoolClassDto createSchoolClass(SchoolClassDto schoolClassDto) {
        SchoolClass schoolClass = convertToEntity(schoolClassDto);
        SchoolClass savedSchoolClass = schoolClassRepository.save(schoolClass);
        return convertToDto(savedSchoolClass);
    }
    
    
    public void deleteSchoolClass(Long id) {
        schoolClassRepository.deleteById(id);
    }
    
    private SchoolClassDto convertToDto(SchoolClass schoolClass) {
        SchoolClassDto schoolClassDto = modelMapper.map(schoolClass, SchoolClassDto.class);
        if (schoolClass.getTeacher() != null) {
        }
        return schoolClassDto;
    }
    
    private SchoolClass convertToEntity(SchoolClassDto schoolClassDto) {
        SchoolClass schoolClass = modelMapper.map(schoolClassDto, SchoolClass.class);
        return schoolClass;
    }

}
