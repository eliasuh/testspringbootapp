package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import com.example.demo.Dto.StudentDto;
import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import lombok.AllArgsConstructor;

@Service    
@AllArgsConstructor
@EnableCaching
public class StudentImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;
    @Autowired
    ModelMapper modelMapper;
   
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);     
         studentRepo.save(student); 
        return studentDto;
    }
   
    @Cacheable(value="StudentDto")
    public List<StudentDto> getAllStudents() {
        //doLongRunningTask();
        List<Student> students = studentRepo.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student s : students)
        {
            studentDtos.add(StdentToStudentDto(s));
        }
        return studentDtos;       
    }
   
    @Cacheable(value="StudentDto", key="#id")
    public StudentDto getStudentById(Long id) {
         List<Student> students = studentRepo.findAll();
         Student student = null;
         for(Student s : students )
         {
            if(s.getId()==id)
            {
                student = s;
            }
         }
         return StdentToStudentDto(student);
    } 
  
    @CacheEvict(value ="StudentDto" ,allEntries = true)
    //@CachePut(value = "StudentDto",key = "#StudentDto.id") 
    public StudentDto updateStudent(StudentDto studentDto) {
         doLongRunningTask();
        Student student = modelMapper.map(studentDto,Student.class);     
         studentRepo.save(student); 
        return studentDto;
    }
    @Override
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }
   
    public Student StdentDtoToStudent(StudentDto StudentDto) {
       Student Student = this.modelMapper.map(StudentDto, Student.class);
       return Student;
    }

    public StudentDto StdentToStudentDto(Student student) {
       StudentDto studentdto = this.modelMapper.map(student, StudentDto.class);
       return studentdto;
    }

    private void doLongRunningTask() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
   
}
