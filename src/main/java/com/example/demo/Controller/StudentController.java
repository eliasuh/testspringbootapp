package com.example.demo.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.StudentDto;
import com.example.demo.Service.StudentImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class StudentController {
    
    @Autowired
    StudentImpl studentImpl;

    @RequestMapping(path="/student/getall", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> getAllStudents()
    {
        try {
            List<StudentDto> student =  studentImpl.getAllStudents();
              System.out.println("Get All Students"+student);
             return new ResponseEntity<>(student,HttpStatus.OK);
           
        } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }      
    }

    @RequestMapping(path="/student/add", method = RequestMethod.POST)
    public ResponseEntity< StudentDto> addStudent(@RequestBody StudentDto studentDto){
        try {
            StudentDto addStudent  =  studentImpl.addStudent(studentDto);
              System.out.println("Add Student"+addStudent);
            return new ResponseEntity<>(addStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }        
    }

    @RequestMapping(path="/student/get/{id}",  method = RequestMethod.GET)
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id)
    {
        try {
            StudentDto studentdto = studentImpl.getStudentById(id);
               System.out.println("Get Student by Id"+studentdto.toString());
             return new ResponseEntity<>(studentdto, HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }  
    }

    @RequestMapping(path="/student/update/{id}",  method = RequestMethod.PUT)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto student)
    {
        try {
              StudentDto exitingstudent = (StudentDto) studentImpl.getStudentById(id);
               System.out.println("exitingstudent"+exitingstudent);
              if (student.getFirstName()!=null && !student.getFirstName().isEmpty()) {
                exitingstudent.setFirstName(student.getFirstName());
              }
            if (student.getLastName()!=null && !student.getLastName().isEmpty()) {
                exitingstudent.setLastName(student.getLastName());
              }
               if (student.getEmail()!=null && !student.getEmail().isEmpty()) {
                exitingstudent.setEmail(student.getEmail());
              }
              StudentDto updatedstudent = studentImpl.updateStudent(exitingstudent);
                System.out.println("Update Student"+updatedstudent);
             return new ResponseEntity<>(updatedstudent, HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }    
    }

     @RequestMapping(path="/student/delete/{id}",  method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable Long id)
    {
        studentImpl.deleteStudentById(id);      
    }
}
