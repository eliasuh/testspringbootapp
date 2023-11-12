package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.StudentDto;


public interface StudentService {
    public StudentDto addStudent(StudentDto studentDto);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto updateStudent(StudentDto studentDto);
    void deleteStudentById(Long id);
}
