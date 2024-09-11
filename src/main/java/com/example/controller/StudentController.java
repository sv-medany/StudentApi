package com.example.controller;

import com.example.services.StudentService;
import com.example.models.Student;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Controller("/api/students")
public class StudentController {
    private  final StudentService serv;
    public StudentController(StudentService serv) {
        this.serv = serv;
    }
    @Post
    @Operation(summary = "create new student")
    @Tag(name = "Create Student")
    public HttpResponse<Student> createStudent(@Body Student student){
        return  HttpResponse.created(serv.createStudent(student));
    }
    @Get
    @Operation(summary = "get all students")
    @Tag(name = "getAllStudents")
    public HttpResponse<List<Student>> getAllStudents(){
        return  HttpResponse.ok(serv.getAllStudents());
    }
    @Get("/{id}")
    @Operation(summary = "get a student by id if exists")
    @Tag(name = "getStudentbyId")
    public HttpResponse<Student>getStudent(@PathVariable int id){
        return  HttpResponse.ok(serv.getStudent(id));
    }
    @Put("/{id}")
    @Operation(summary = "update a student record using his id")
    @Tag(name = "updateStudentbyID")
    public  HttpResponse<Student> updateStudent(@PathVariable int id ,@Body Student student){
        return  HttpResponse.ok(serv.updateStudent(id,student));
    }
}
