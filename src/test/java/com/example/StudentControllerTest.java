package com.example;

import com.example.controller.StudentController;
import com.example.models.Student;
import com.example.services.StudentService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MicronautTest
public class StudentControllerTest {
    @Mock
    private StudentService myService;
    @InjectMocks // This injects the mock repository into the service
    private StudentController controller;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetStudent() {
        // Given
        int id = 1;
        Student student = new Student();
        student.setId(id);
        student.setName("John Doe");
        student.setEmail("john@example.com");
        student.setGrade(90);
        when(myService.getStudent(id)).thenReturn(student);
        // When
        HttpResponse<Student> response = controller.getStudent(id);
        // Then
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(student, response.getBody().orElse(null));
    }
    @Test
    public void getAllStudents(){
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("John Doe");
        student1.setEmail("john@example.com");
        student1.setGrade(90);
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Jane Doe");
        student2.setEmail("jane@example.com");
        student2.setGrade(30);
        List<Student> students = new ArrayList<Student>() ;
        students.add(student1);
        students.add(student2);
        when(myService.getAllStudents()).thenReturn(students);
        assertEquals(2, controller.getAllStudents().body().size());
    }
    @Test
    void testCreateStudent() {
        // Given
        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john@example.com");
        student.setGrade(90);
        when(myService.createStudent(any(Student.class))).thenReturn(student);
        // When
        HttpResponse<Student> response = controller.createStudent(student);
        // Then
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals(student, response.getBody().orElse(null));
    }
}
