package com.example.services;

import com.example.repo.StudentRepository;
import com.example.models.Student;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class StudentService {
    private  final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    
    public Student createStudent(Student student){
        return  repo.save(student);
    }
    public List<Student> getAllStudents(){
        return repo.findAll();
    }
    public Student getStudent(int id){
        return repo.findById(id).orElse(null);
    }
    public Student updateStudent(int id, Student student){
        Student prevStudent = getStudent(id);
        prevStudent.setEmail(student.getEmail());
        prevStudent.setGrade(student.getGrade());
        prevStudent.setName(student.getName());
        return  repo.update(prevStudent);
    }
}
